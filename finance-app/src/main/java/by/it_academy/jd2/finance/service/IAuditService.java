package by.it_academy.jd2.finance.service;


import by.it_academy.jd2.finance.service.dto.auditUnit.AuditUnitCreateDto;
import by.it_academy.jd2.finance.service.dto.auditUnit.AuditUnitOutDto;
import by.it_academy.jd2.finance.service.dto.page.PageDto;
import by.it_academy.jd2.finance.service.dto.page.PageOf;

import java.util.UUID;

public interface IAuditService {

    void create(AuditUnitCreateDto dto);

    AuditUnitOutDto getById(UUID id);

    PageOf<AuditUnitOutDto> getAll(PageDto pageDto);

}
