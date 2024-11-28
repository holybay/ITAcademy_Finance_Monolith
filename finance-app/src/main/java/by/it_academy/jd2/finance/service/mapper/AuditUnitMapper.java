package by.it_academy.jd2.finance.service.mapper;

import by.it_academy.jd2.finance.repository.entity.auditUnit.AuditUnit;
import by.it_academy.jd2.finance.repository.entity.user.User;
import by.it_academy.jd2.finance.service.dto.auditUnit.AuditUnitCreateDto;
import by.it_academy.jd2.finance.service.dto.auditUnit.AuditUnitOutDto;
import by.it_academy.jd2.finance.service.dto.user.UserAuditOutDto;
import org.springframework.stereotype.Component;

@Component
public class AuditUnitMapper {

    public AuditUnit toEntity(AuditUnitCreateDto dto, User user) {
        return AuditUnit.builder()
                        .setUser(user)
                        .setText(dto.getText())
                        .setType(dto.getType())
                        .setEssenceTypeId(dto.getEssenceTypeId())
                        .build();
    }

    public AuditUnitOutDto toOutDto(AuditUnit entity, UserAuditOutDto user) {
        return AuditUnitOutDto.builder()
                              .setId(entity.getId())
                              .setCreatedAt(entity.getCreatedAt())
                              .setUpdatedAt(entity.getUpdatedAt())
                              .setUser(user)
                              .setText(entity.getText())
                              .setType(entity.getType())
                              .setEssenceTypeId(entity.getEssenceTypeId())
                              .build();
    }
}
