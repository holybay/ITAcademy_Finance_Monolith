package by.it_academy.jd2.finance.service.mapper;

import by.it_academy.jd2.finance.repository.entity.Operation;
import by.it_academy.jd2.finance.repository.entity.Currency;
import by.it_academy.jd2.finance.repository.entity.Category;
import by.it_academy.jd2.finance.repository.entity.account.Account;
import by.it_academy.jd2.finance.service.dto.UpdateCoordinate;
import by.it_academy.jd2.finance.service.dto.operation.OperationCreateDto;
import by.it_academy.jd2.finance.service.dto.operation.OperationOutDto;
import by.it_academy.jd2.finance.service.dto.operation.OperationUpdateDto;
import org.springframework.stereotype.Component;

@Component
public class OperationMapper {

    public Operation toEntity(OperationCreateDto dto, Account account, Currency currency,
                              Category category) {
        return Operation.builder()
                        .setDate(dto.getDate())
                        .setDescription(dto.getDescription())
                        .setCategory(category)
                        .setValue(dto.getValue())
                        .setCurrency(currency)
                        .setAccount(account)
                        .build();
    }

    public Operation toEntity(OperationUpdateDto dto, UpdateCoordinate coordinate, Account account, Currency currency,
                              Category category) {
        return Operation.builder()
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


    public OperationOutDto toOutDto(Operation entity) {
        return OperationOutDto.builder()
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
