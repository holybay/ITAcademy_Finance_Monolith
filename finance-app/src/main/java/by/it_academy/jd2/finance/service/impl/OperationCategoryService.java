package by.it_academy.jd2.finance.service.impl;

import by.it_academy.jd2.finance.config.property.PageProperty;
import by.it_academy.jd2.finance.platform.util.PageUtil;
import by.it_academy.jd2.finance.repository.IOperationCategoryRepository;
import by.it_academy.jd2.finance.repository.entity.OperationCategory;
import by.it_academy.jd2.finance.service.IOperationCategoryService;
import by.it_academy.jd2.finance.service.dto.classifier.OperationCategoryDto;
import by.it_academy.jd2.finance.service.dto.page.PageDto;
import by.it_academy.jd2.finance.service.dto.page.PageOf;
import by.it_academy.jd2.finance.service.mapper.ClassifierMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OperationCategoryService implements IOperationCategoryService {

    private final IOperationCategoryRepository categoryRepository;
    private final ClassifierMapper mapper;
    private final PageProperty pageProperty;

    public OperationCategoryService(IOperationCategoryRepository categoryRepository, ClassifierMapper mapper,
                                    PageProperty pageProperty) {
        this.categoryRepository = categoryRepository;
        this.mapper = mapper;
        this.pageProperty = pageProperty;
    }

    @Override
    @Transactional
    public void create(OperationCategoryDto dto) {
        categoryRepository.saveAndFlush(mapper.toEntity(dto));
    }

    @Override
    @Transactional(readOnly = true)
    public PageOf<OperationCategoryDto> getAll(PageDto pageDto) {
        Sort.TypedSort<OperationCategory> category = Sort.sort(OperationCategory.class);
        Sort sort = category.by(OperationCategory::getCreatedAt).descending().and(
                category.by(OperationCategory::getTitle).ascending());
        PageRequest pageReq = PageUtil.getPageRequest(pageDto.getPage(), pageDto.getSize(), pageProperty,
                categoryRepository.count(), sort);
        Page<OperationCategoryDto> categoryPage = categoryRepository.findAll(pageReq)
                                                                    .map(mapper::toCategoryDto);
        return PageOf.convert(categoryPage);
    }
}
