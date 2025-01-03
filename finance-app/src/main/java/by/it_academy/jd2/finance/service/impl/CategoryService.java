package by.it_academy.jd2.finance.service.impl;

import by.it_academy.jd2.finance.config.property.PageProperty;
import by.it_academy.jd2.finance.platform.util.PageUtil;
import by.it_academy.jd2.finance.repository.ICategoryRepository;
import by.it_academy.jd2.finance.repository.entity.Category;
import by.it_academy.jd2.finance.repository.entity.auditUnit.EEssenceType;
import by.it_academy.jd2.finance.service.IAuditService;
import by.it_academy.jd2.finance.service.ICategoryService;
import by.it_academy.jd2.finance.service.dto.auditUnit.AuditUnitCreateDto;
import by.it_academy.jd2.finance.service.dto.classifier.CategoryDto;
import by.it_academy.jd2.finance.service.dto.page.PageDto;
import by.it_academy.jd2.finance.service.dto.page.PageOf;
import by.it_academy.jd2.finance.service.mapper.ClassifierMapper;
import by.it_academy.jd2.finance.service.util.JwtTokenHandler;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;
import java.util.UUID;

@Service
public class CategoryService implements ICategoryService {

    private final ICategoryRepository categoryRepository;
    private final ClassifierMapper mapper;
    private final PageProperty pageProperty;
    private final IAuditService auditService;
    private final JwtTokenHandler tokenHandler;

    public CategoryService(ICategoryRepository categoryRepository, ClassifierMapper mapper,
                           PageProperty pageProperty, IAuditService auditService, JwtTokenHandler tokenHandler) {
        this.categoryRepository = categoryRepository;
        this.mapper = mapper;
        this.pageProperty = pageProperty;
        this.auditService = auditService;
        this.tokenHandler = tokenHandler;
    }

    @Override
    @Transactional
    public void create(CategoryDto dto, String token) {
        Category entity = mapper.toEntity(dto);
        entity.setId(UUID.randomUUID());
        categoryRepository.saveAndFlush(entity);
        auditService.create(AuditUnitCreateDto.builder()
                                              .setUserId(tokenHandler.getTokenDto(token).getUserId())
                                              .setText("Created a new Category!")
                                              .setType(EEssenceType.CATEGORY)
                                              .setEssenceTypeId(entity.getId())
                                              .build());
    }

    @Override
    @Transactional(readOnly = true)
    public Category getById(UUID id) {
        return categoryRepository.findById(id).orElseThrow(() ->
                new NoSuchElementException("Haven't found a category with id: " + id));
    }

    @Override
    @Transactional(readOnly = true)
    public PageOf<CategoryDto> getAll(PageDto pageDto) {
        Sort.TypedSort<Category> category = Sort.sort(Category.class);
        Sort sort = category.by(Category::getCreatedAt).descending().and(
                category.by(Category::getTitle).ascending());
        PageRequest pageReq = PageUtil.getPageRequest(pageDto.getPage(), pageDto.getSize(), pageProperty,
                categoryRepository.count(), sort);
        Page<CategoryDto> categoryPage = categoryRepository.findAll(pageReq)
                                                           .map(mapper::toCategoryDto);
        return PageOf.convert(categoryPage);
    }
}
