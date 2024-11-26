package by.it_academy.jd2.finance.service;

import by.it_academy.jd2.finance.repository.entity.user.User;
import by.it_academy.jd2.finance.service.dto.UpdateCoordinate;
import by.it_academy.jd2.finance.service.dto.page.PageDto;
import by.it_academy.jd2.finance.service.dto.user.UserCreateDto;
import by.it_academy.jd2.finance.service.dto.user.UserSelfCreateDto;
import by.it_academy.jd2.finance.service.dto.user.UserUpdateDto;
import org.springframework.data.domain.Page;

import java.util.UUID;

public interface IUserService {

    void create(UserCreateDto createDto);

    void create(UserSelfCreateDto createDto);

    User getById(UUID id);

    User getByMail(String mail);

    Page<User> getAll(PageDto pageDto);

    void update(UserUpdateDto updateDto, UpdateCoordinate coordinate);
}