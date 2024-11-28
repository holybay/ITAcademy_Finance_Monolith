package by.it_academy.jd2.finance.service.impl;

import by.it_academy.jd2.finance.config.property.PageProperty;
import by.it_academy.jd2.finance.platform.exception.AppAuthzException;
import by.it_academy.jd2.finance.platform.exception.ValidationException;
import by.it_academy.jd2.finance.platform.util.PageUtil;
import by.it_academy.jd2.finance.repository.IAccountOperationRepository;
import by.it_academy.jd2.finance.repository.entity.AccountOperation;
import by.it_academy.jd2.finance.repository.entity.Currency;
import by.it_academy.jd2.finance.repository.entity.account.Account;
import by.it_academy.jd2.finance.service.IAccountOperationService;
import by.it_academy.jd2.finance.service.IAccountService;
import by.it_academy.jd2.finance.service.ICurrencyService;
import by.it_academy.jd2.finance.service.IOperationCategoryService;
import by.it_academy.jd2.finance.service.dto.UpdateCoordinate;
import by.it_academy.jd2.finance.service.dto.operation.AccountOperationCreateDto;
import by.it_academy.jd2.finance.service.dto.operation.AccountOperationOutDto;
import by.it_academy.jd2.finance.service.dto.operation.AccountOperationUpdateDto;
import by.it_academy.jd2.finance.service.dto.page.PageDto;
import by.it_academy.jd2.finance.service.dto.page.PageOf;
import by.it_academy.jd2.finance.service.mapper.AccountOperationMapper;
import by.it_academy.jd2.finance.service.util.JwtTokenHandler;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.NoSuchElementException;
import java.util.UUID;

@Service
public class AccountOperationService implements IAccountOperationService {

    private final IAccountOperationRepository operationRepository;
    private final IAccountService accountService;
    private final ICurrencyService currencyService;
    private final IOperationCategoryService categoryService;
    private final JwtTokenHandler tokenHandler;
    private final AccountOperationMapper mapper;
    private final PageProperty pageProperty;

    public AccountOperationService(IAccountOperationRepository operationRepository, IAccountService accountService, ICurrencyService currencyService, IOperationCategoryService categoryService, JwtTokenHandler tokenHandler, AccountOperationMapper mapper, PageProperty pageProperty) {
        this.operationRepository = operationRepository;
        this.accountService = accountService;
        this.currencyService = currencyService;
        this.categoryService = categoryService;
        this.tokenHandler = tokenHandler;
        this.mapper = mapper;
        this.pageProperty = pageProperty;
    }

    @Override
    @Transactional(rollbackFor = {SQLException.class})
    public void create(UUID accountId, AccountOperationCreateDto createDto, String token) {
        UUID userId = tokenHandler.getTokenDto(token).getUserId();
        Account account = accountService.getById(accountId, userId);

        authzCheck(account, userId);
        operationAmountCheck(createDto.getValue());

        Currency currency = currencyService.getById(createDto.getCurrencyId());
        currencyTypeCheck(account, currency);

        account.setBalance(account.getBalance().add(createDto.getValue()));

        operationRepository.saveAndFlush(mapper.toEntity(createDto, account, currency,
                categoryService.getById(createDto.getCategoryId())));
    }

    @Override
    public AccountOperation getById(UUID id) {
        return operationRepository.findById(id).orElseThrow(() ->
                new NoSuchElementException(String.format(
                        "Haven't found the account operation with id = [%s]!", id)));
    }

    @Override
    @Transactional(readOnly = true)
    public PageOf<AccountOperationOutDto> getAll(UUID accountId, PageDto pageDto, String token) {
        UUID userId = tokenHandler.getTokenDto(token).getUserId();
        Account account = accountService.getById(accountId, userId);

        authzCheck(account, userId);

        PageUtil.validatePage(pageDto);
        Sort.TypedSort<AccountOperation> operation = Sort.sort(AccountOperation.class);
        Sort sort = operation.by(AccountOperation::getDate).descending();
        PageRequest pageReq = PageUtil.getPageRequest(pageDto.getPage(), pageDto.getSize(), pageProperty,
                operationRepository.countByAccountId(accountId), sort);
        Page<AccountOperationOutDto> operationPage = operationRepository.findAllByAccountId(pageReq, accountId)
                                                                        .map(mapper::toOutDto);
        return PageOf.convert(operationPage);
    }

    @Override
    @Transactional
    public void update(AccountOperationUpdateDto updateDto, UpdateCoordinate coordinate, UUID accountId, String token) {
        AccountOperation operation = this.getById(coordinate.getId());
        UUID userId = tokenHandler.getTokenDto(token).getUserId();

        authzCheck(operation.getAccount(), userId);
        operationToAccountCheck(accountId, operation);
        operationAmountCheck(updateDto.getValue());

        Currency currency = currencyService.getById(updateDto.getCurrencyId());
        currencyTypeCheck(operation.getAccount(), currency);

        if (!operation.getUpdatedAt().isEqual(coordinate.getUpdatedAt())) {
            throw new IllegalArgumentException("Updated data is outdated!");
        }

        operation.setDate(updateDto.getDate());
        operation.setDescription(updateDto.getDescription());
        operation.setCategory(categoryService.getById(updateDto.getCategoryId()));
        operation.setValue(updateDto.getValue());
        operation.setCurrency(currency);
        operationRepository.saveAndFlush(operation);
    }

    @Override
    @Transactional
    public void delete(UpdateCoordinate coordinate, UUID accountId, String token) {
        AccountOperation operation = this.getById(coordinate.getId());
        UUID userId = tokenHandler.getTokenDto(token).getUserId();

        authzCheck(operation.getAccount(), userId);

        if (!operation.getUpdatedAt().isEqual(coordinate.getUpdatedAt())) {
            throw new IllegalArgumentException("Failed to delete! The data has already been updated!");
        }
        operationRepository.delete(operation);
    }

    private void authzCheck(Account account, UUID userId) {
        if (!account.getUser().getId().equals(userId)) {
            throw new AppAuthzException(String.format(
                    "User with id = %s is not authorized to make adjustments in the account id = %s",
                    userId, account.getId()));
        }
    }

    private void operationAmountCheck(BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO) == 0) {
            throw new ValidationException("value", "Operation amount can't be equal to 0!");
        }
    }

    private void operationToAccountCheck(UUID accountId, AccountOperation operation) {
        if (!operation.getAccount().getId().equals(accountId)) {
            throw new ValidationException("{uuid_operation}",
                    String.format("The operation with id {%s} doesn't belong to account with id {%s}",
                            operation.getId(), accountId));
        }
    }

    private void currencyTypeCheck(Account account, Currency currency) {
        if (!account.getCurrency().equals(currency)) {
            throw new ValidationException("currency", "Operation currency type differs from the account currency type!");
        }
    }
}
