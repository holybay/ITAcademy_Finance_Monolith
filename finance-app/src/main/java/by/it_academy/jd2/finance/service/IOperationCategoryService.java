package by.it_academy.jd2.finance.service;


import by.it_academy.jd2.finance.repository.entity.OperationCategory;
import by.it_academy.jd2.finance.service.dto.classifier.OperationCategoryDto;
import by.it_academy.jd2.finance.service.dto.page.PageDto;
import by.it_academy.jd2.finance.service.dto.page.PageOf;

import java.util.UUID;

public interface IOperationCategoryService {

    void create(OperationCategoryDto dto);

    OperationCategory getById(UUID id);

    PageOf<OperationCategoryDto> getAll(PageDto pageDto);

}
