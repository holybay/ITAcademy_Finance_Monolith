package by.it_academy.jd2.finance.service;


import by.it_academy.jd2.finance.repository.entity.Operation;
import by.it_academy.jd2.finance.service.dto.UpdateCoordinate;
import by.it_academy.jd2.finance.service.dto.operation.OperationCreateDto;
import by.it_academy.jd2.finance.service.dto.operation.OperationOutDto;
import by.it_academy.jd2.finance.service.dto.operation.OperationUpdateDto;
import by.it_academy.jd2.finance.service.dto.page.PageDto;
import by.it_academy.jd2.finance.service.dto.page.PageOf;

import java.util.UUID;

public interface IOperationService {

    void create(UUID accountId, OperationCreateDto createDto, String token);

    Operation getById(UUID id);

    PageOf<OperationOutDto> getAll(UUID accountId, PageDto pageDto, String token);

    void update(OperationUpdateDto updateDto, UpdateCoordinate coordinate, UUID accountId, String token);

    void delete(UpdateCoordinate coordinate, UUID accountId, String token);
}
