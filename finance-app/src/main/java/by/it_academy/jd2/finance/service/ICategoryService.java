package by.it_academy.jd2.finance.service;


import by.it_academy.jd2.finance.repository.entity.Category;
import by.it_academy.jd2.finance.service.dto.classifier.CategoryDto;
import by.it_academy.jd2.finance.service.dto.page.PageDto;
import by.it_academy.jd2.finance.service.dto.page.PageOf;

import java.util.UUID;

public interface ICategoryService {

    void create(CategoryDto dto);

    Category getById(UUID id);

    PageOf<CategoryDto> getAll(PageDto pageDto);

}
