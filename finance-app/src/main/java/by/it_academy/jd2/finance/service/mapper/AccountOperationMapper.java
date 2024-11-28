package by.it_academy.jd2.finance.service.mapper;

import by.it_academy.jd2.finance.repository.entity.AccountOperation;
import by.it_academy.jd2.finance.repository.entity.Currency;
import by.it_academy.jd2.finance.repository.entity.OperationCategory;
import by.it_academy.jd2.finance.repository.entity.account.Account;
import by.it_academy.jd2.finance.service.dto.UpdateCoordinate;
import by.it_academy.jd2.finance.service.dto.operation.AccountOperationCreateDto;
import by.it_academy.jd2.finance.service.dto.operation.AccountOperationOutDto;
import by.it_academy.jd2.finance.service.dto.operation.AccountOperationUpdateDto;
import org.springframework.stereotype.Component;

@Component
public class AccountOperationMapper {

    public AccountOperation toEntity(AccountOperationCreateDto dto, Account account, Currency currency,
                                     OperationCategory category) {
        return AccountOperation.builder()
                               .setDate(dto.getDate())
                               .setDescription(dto.getDescription())
                               .setCategory(category)
                               .setValue(dto.getValue())
                               .setCurrency(currency)
                               .setAccount(account)
                               .build();
    }

    public AccountOperation toEntity(AccountOperationUpdateDto dto, UpdateCoordinate coordinate, Account account, Currency currency,
                                     OperationCategory category) {
        return AccountOperation.builder()
                               .setId(coordinate.getId())
                               .setUpdatedAt(coordinate.getUpdatedAt())
                               .setDate(dto.getDate())
                               .setDescription(dto.getDescription())
                               .setCategory(category)
                               .setValue(dto.getValue())
                               .setCurrency(currency)
                               .setAccount(account)
                               .build();
    }


    public AccountOperationOutDto toOutDto(AccountOperation entity) {
        return AccountOperationOutDto.builder()
                                     .setId(entity.getId())
                                     .setCreatedAt(entity.getCreatedAt())
                                     .setUpdatedAt(entity.getUpdatedAt())
                                     .setDate(entity.getDate())
                                     .setCategoryId(entity.getCategory().getId())
                                     .setDescription(entity.getDescription())
                                     .setValue(entity.getValue())
                                     .setCurrencyId(entity.getCurrency().getId())
                                     .build();
    }
}
