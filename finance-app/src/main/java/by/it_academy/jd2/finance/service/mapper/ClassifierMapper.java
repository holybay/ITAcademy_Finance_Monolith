package by.it_academy.jd2.finance.service.mapper;

import by.it_academy.jd2.finance.repository.entity.Currency;
import by.it_academy.jd2.finance.repository.entity.OperationCategory;
import by.it_academy.jd2.finance.service.dto.classifier.CurrencyDto;
import by.it_academy.jd2.finance.service.dto.classifier.OperationCategoryDto;
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

    public OperationCategory toEntity(OperationCategoryDto dto) {
        return OperationCategory.builder()
                                .setId(dto.getId())
                                .setCreatedAt(dto.getCreatedAt())
                                .setUpdatedAt(dto.getUpdatedAt())
                                .setTitle(dto.getTitle())
                                .build();
    }

    public OperationCategoryDto toCategoryDto(OperationCategory entity) {
        return OperationCategoryDto.builder()
                                   .setId(entity.getId())
                                   .setCreatedAt(entity.getCreatedAt())
                                   .setUpdatedAt(entity.getUpdatedAt())
                                   .setTitle(entity.getTitle())
                                   .build();
    }
}
