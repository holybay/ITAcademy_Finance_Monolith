package by.it_academy.jd2.finance.service.mapper;

import by.it_academy.jd2.finance.repository.entity.Currency;
import by.it_academy.jd2.finance.repository.entity.account.Account;
import by.it_academy.jd2.finance.repository.entity.account.EAccountType;
import by.it_academy.jd2.finance.repository.entity.user.User;
import by.it_academy.jd2.finance.service.dto.UpdateCoordinate;
import by.it_academy.jd2.finance.service.dto.account.AccountCreateDto;
import by.it_academy.jd2.finance.service.dto.account.AccountOutDto;
import by.it_academy.jd2.finance.service.dto.account.AccountUpdateDto;
import org.springframework.stereotype.Component;

@Component
public class AccountMapper {

    public Account toEntity(AccountCreateDto dto, User user, Currency currency) {
        return Account.builder()
                      .setTitle(dto.getTitle())
                      .setDescription(dto.getDescription())
                      .setType(Enum.valueOf(EAccountType.class, dto.getType()))
                      .setCurrency(currency)
                      .setUser(user)
                      .build();
    }

    public Account toEntity(AccountUpdateDto dto, UpdateCoordinate coordinate, User user, Currency currency) {
        return Account.builder()
                      .setId(coordinate.getId())
                      .setUpdatedAt(coordinate.getUpdatedAt())
                      .setTitle(dto.getTitle())
                      .setDescription(dto.getDescription())
                      .setType(Enum.valueOf(EAccountType.class, dto.getType()))
                      .setCurrency(currency)
                      .setUser(user)
                      .build();
    }

    public AccountOutDto toOutDto(Account entity) {
        return AccountOutDto.builder()
                            .setId(entity.getId())
                            .setCreatedAt(entity.getCreatedAt())
                            .setUpdatedAt(entity.getUpdatedAt())
                            .setTitle(entity.getTitle())
                            .setBalance(entity.getBalance())
                            .setDescription(entity.getDescription())
                            .setType(entity.getType())
                            .setCurrencyId(entity.getCurrency().getId())
                            .build();
    }
}
