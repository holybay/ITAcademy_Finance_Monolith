package by.it_academy.jd2.finance.service;


import by.it_academy.jd2.finance.repository.entity.AccountOperation;
import by.it_academy.jd2.finance.service.dto.UpdateCoordinate;
import by.it_academy.jd2.finance.service.dto.operation.AccountOperationCreateDto;
import by.it_academy.jd2.finance.service.dto.operation.AccountOperationOutDto;
import by.it_academy.jd2.finance.service.dto.operation.AccountOperationUpdateDto;
import by.it_academy.jd2.finance.service.dto.page.PageDto;
import by.it_academy.jd2.finance.service.dto.page.PageOf;

import java.util.UUID;

public interface IAccountOperationService {

    void create(UUID accountId, AccountOperationCreateDto createDto, String token);

    AccountOperation getById(UUID id);

    PageOf<AccountOperationOutDto> getAll(UUID accountId, PageDto pageDto, String token);

    void update(AccountOperationUpdateDto updateDto, UpdateCoordinate coordinate, UUID accountId, String token);

    void delete(UpdateCoordinate coordinate, UUID accountId, String token);
}
