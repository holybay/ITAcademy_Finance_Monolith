package by.it_academy.jd2.finance.service.impl;

import by.it_academy.jd2.finance.config.property.PageProperty;
import by.it_academy.jd2.finance.platform.util.PageUtil;
import by.it_academy.jd2.finance.repository.IAccountRepository;
import by.it_academy.jd2.finance.repository.entity.account.Account;
import by.it_academy.jd2.finance.repository.entity.account.EAccountType;
import by.it_academy.jd2.finance.service.IAccountService;
import by.it_academy.jd2.finance.service.ICurrencyService;
import by.it_academy.jd2.finance.service.IUserService;
import by.it_academy.jd2.finance.service.dto.UpdateCoordinate;
import by.it_academy.jd2.finance.service.dto.account.AccountCreateDto;
import by.it_academy.jd2.finance.service.dto.account.AccountOutDto;
import by.it_academy.jd2.finance.service.dto.account.AccountUpdateDto;
import by.it_academy.jd2.finance.service.dto.page.PageDto;
import by.it_academy.jd2.finance.service.dto.page.PageOf;
import by.it_academy.jd2.finance.service.mapper.AccountMapper;
import by.it_academy.jd2.finance.service.util.JwtTokenHandler;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.NoSuchElementException;
import java.util.UUID;

@Service
public class AccountService implements IAccountService {

    private final IAccountRepository accountRepository;
    private final IUserService userService;
    private final ICurrencyService currencyService;
    private final AccountMapper mapper;
    private final JwtTokenHandler tokenHandler;
    private final PageProperty pageProperty;

    public AccountService(IAccountRepository accountRepository, IUserService userService, ICurrencyService currencyService,
                          AccountMapper mapper, JwtTokenHandler tokenHandler, PageProperty pageProperty) {
        this.accountRepository = accountRepository;
        this.userService = userService;
        this.currencyService = currencyService;
        this.mapper = mapper;
        this.tokenHandler = tokenHandler;
        this.pageProperty = pageProperty;
    }

    @Override
    @Transactional(rollbackFor = {SQLException.class})
    public void create(AccountCreateDto createDto, String token) {
        UUID userId = tokenHandler.getTokenDto(token).getUserId();
        accountRepository.saveAndFlush(mapper.toEntity(createDto, userService.getById(userId),
                currencyService.getById(createDto.getCurrencyId())));
    }

    @Override
    @Transactional(readOnly = true)
    public AccountOutDto getById(UUID id, String token) {
        UUID userId = tokenHandler.getTokenDto(token).getUserId();
        return accountRepository.findByIdAndUserId(id, userId)
                                .map(mapper::toOutDto)
                                .orElseThrow(() -> new NoSuchElementException(
                                        String.format("Haven't found an account with id = [%s] for user: %s !", id, userId)));
    }

    @Override
    @Transactional(readOnly = true)
    public Account getById(UUID id, UUID userId) {
        return accountRepository.findByIdAndUserId(id, userId)
                                .orElseThrow(() -> new NoSuchElementException(
                                        String.format("Haven't found an account with id = [%s] for user: %s !", id, userId)));
    }

    @Override
    @Transactional(readOnly = true)
    public PageOf<AccountOutDto> getAll(PageDto pageDto, String token) {
        UUID userId = tokenHandler.getTokenDto(token).getUserId();
        PageUtil.validatePage(pageDto);
        Sort.TypedSort<Account> account = Sort.sort(Account.class);
        Sort sort = account.by(Account::getCreatedAt).descending().and(account.by(Account::getTitle).ascending());
        PageRequest pageReq = PageUtil.getPageRequest(pageDto.getPage(), pageDto.getSize(), pageProperty,
                accountRepository.countByUserId(userId), sort);
        Page<AccountOutDto> accountPage = accountRepository.findAllByUserId(pageReq, userId)
                                                           .map(mapper::toOutDto);
        return PageOf.convert(accountPage);
    }

    @Override
    @Transactional(rollbackFor = {SQLException.class})
    public void update(AccountUpdateDto updateDto, UpdateCoordinate coordinate, String token) {
        UUID userId = tokenHandler.getTokenDto(token).getUserId();
        Account account = this.getById(coordinate.getId(), userId);
        if (!account.getUpdatedAt().isEqual(coordinate.getUpdatedAt())) {
            throw new IllegalArgumentException("Updated data is outdated!");
        }
        settingUpdates(updateDto, account);
        accountRepository.saveAndFlush(account);
    }

    private void settingUpdates(AccountUpdateDto updateDto, Account account) {
        account.setTitle(updateDto.getTitle());
        account.setDescription(updateDto.getDescription());
        account.setType(Enum.valueOf(EAccountType.class, updateDto.getType()));
        account.setCurrency(currencyService.getById(updateDto.getCurrencyId()));
    }
}
