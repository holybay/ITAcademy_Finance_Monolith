package by.it_academy.jd2.finance.service.mapper;

import by.it_academy.jd2.finance.repository.entity.user.EUserRole;
import by.it_academy.jd2.finance.repository.entity.user.EUserStatus;
import by.it_academy.jd2.finance.repository.entity.user.User;
import by.it_academy.jd2.finance.service.dto.UpdateCoordinate;
import by.it_academy.jd2.finance.service.dto.user.UserCreateDto;
import by.it_academy.jd2.finance.service.dto.user.UserOutDto;
import by.it_academy.jd2.finance.service.dto.user.UserSelfCreateDto;
import by.it_academy.jd2.finance.service.dto.user.UserUpdateDto;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public User toEntity(UserCreateDto createDto) {
        return User.builder()
                   .setId(createDto.getId())
                   .setCreatedAt(createDto.getCreatedAt())
                   .setUpdatedAt(createDto.getUpdatedAt())
                   .setMail(createDto.getMail())
                   .setFio(createDto.getFio())
                   .setRole(Enum.valueOf(EUserRole.class, createDto.getRole()))
                   .setStatus(Enum.valueOf(EUserStatus.class, createDto.getStatus()))
                   .setPassword(createDto.getPassword())
                   .build();
    }

    public User toEntity(UserSelfCreateDto createDto) {
        return User.builder()
                   .setId(createDto.getId())
                   .setCreatedAt(createDto.getCreatedAt())
                   .setUpdatedAt(createDto.getUpdatedAt())
                   .setMail(createDto.getMail())
                   .setFio(createDto.getFio())
                   .setRole(Enum.valueOf(EUserRole.class, createDto.getRole()))
                   .setStatus(Enum.valueOf(EUserStatus.class, createDto.getStatus()))
                   .setPassword(createDto.getPassword())
                   .build();
    }

    public User toEntity(UserUpdateDto updateDto, UpdateCoordinate coordinate) {
        return User.builder()
                   .setId(coordinate.getId())
                   .setUpdatedAt(coordinate.getUpdatedAt())
                   .setMail(updateDto.getMail())
                   .setFio(updateDto.getFio())
                   .setRole(Enum.valueOf(EUserRole.class, updateDto.getRole()))
                   .setStatus(Enum.valueOf(EUserStatus.class, updateDto.getStatus()))
                   .setPassword(updateDto.getPassword())
                   .build();
    }

    public UserOutDto toUserOutDto(User entity) {
        return UserOutDto.builder()
                         .setId(entity.getId())
                         .setCreatedAt(entity.getCreatedAt())
                         .setUpdatedAt(entity.getUpdatedAt())
                         .setMail(entity.getMail())
                         .setFio(entity.getFio())
                         .setRole(entity.getRole())
                         .setStatus(entity.getStatus())
                         .build();
    }
}
