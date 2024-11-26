package by.it_academy.jd2.finance.service;


import by.it_academy.jd2.finance.service.dto.classifier.OperationCategoryDto;
import by.it_academy.jd2.finance.service.dto.page.PageDto;
import by.it_academy.jd2.finance.service.dto.page.PageOf;

public interface IOperationCategoryService {

    void create(OperationCategoryDto dto);

    PageOf<OperationCategoryDto> getAll(PageDto pageDto);

}
