package by.it_academy.jd2.finance.service;

import by.it_academy.jd2.finance.service.dto.UpdateCoordinate;
import by.it_academy.jd2.finance.service.dto.page.PageDto;
import by.it_academy.jd2.finance.service.dto.page.PageOf;
import by.it_academy.jd2.finance.service.dto.user.UserCreateDto;
import by.it_academy.jd2.finance.service.dto.user.UserOutDto;
import by.it_academy.jd2.finance.service.dto.user.UserSelfCreateDto;
import by.it_academy.jd2.finance.service.dto.user.UserUpdateDto;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public interface IUserServiceWrapper {

    void create(UserCreateDto createDto);

    void create(UserSelfCreateDto createDto);

    UserOutDto getById(UUID id);

    PageOf<UserOutDto> getAll(PageDto pageDto);

    void update(UserUpdateDto updateDto, UpdateCoordinate coordinate);

    UserOutDto me();
}
