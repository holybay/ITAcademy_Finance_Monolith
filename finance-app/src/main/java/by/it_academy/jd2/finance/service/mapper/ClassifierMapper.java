package by.it_academy.jd2.finance.service.mapper;

import by.it_academy.jd2.finance.repository.entity.Currency;
import by.it_academy.jd2.finance.repository.entity.Category;
import by.it_academy.jd2.finance.service.dto.classifier.CurrencyDto;
import by.it_academy.jd2.finance.service.dto.classifier.CategoryDto;
import org.springframework.stereotype.Component;

@Component
public class ClassifierMapper {

    public Currency toEntity(CurrencyDto dto) {
        return Currency.builder()
                       .setId(dto.getId())
                       .setCreatedAt(dto.getCreatedAt())
                       .setUpdatedAt(dto.getUpdatedAt())
                       .setTitle(dto.getTitle())
                       .setDescription(dto.getDescription())
                       .build();
    }

    public CurrencyDto toCategoryDto(Currency entity) {
        return CurrencyDto.builder()
                          .setId(entity.getId())
                          .setCreatedAt(entity.getCreatedAt())
                          .setUpdatedAt(entity.getUpdatedAt())
                          .setTitle(entity.getTitle())
                          .setDescription(entity.getDescription())
                          .build();
    }

    public Category toEntity(CategoryDto dto) {
        return Category.builder()
                       .setId(dto.getId())
                       .setCreatedAt(dto.getCreatedAt())
                       .setUpdatedAt(dto.getUpdatedAt())
                       .setTitle(dto.getTitle())
                       .build();
    }

    public CategoryDto toCategoryDto(Category entity) {
        return CategoryDto.builder()
                          .setId(entity.getId())
                          .setCreatedAt(entity.getCreatedAt())
                          .setUpdatedAt(entity.getUpdatedAt())
                          .setTitle(entity.getTitle())
                          .build();
    }
}
