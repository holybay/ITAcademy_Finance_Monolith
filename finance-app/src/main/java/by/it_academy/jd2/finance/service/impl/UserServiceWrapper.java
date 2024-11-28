package by.it_academy.jd2.finance.service.impl;

import by.it_academy.jd2.finance.service.IUserService;
import by.it_academy.jd2.finance.service.IUserServiceWrapper;
import by.it_academy.jd2.finance.service.dto.UpdateCoordinate;
import by.it_academy.jd2.finance.service.dto.page.PageDto;
import by.it_academy.jd2.finance.service.dto.page.PageOf;
import by.it_academy.jd2.finance.service.dto.user.UserCreateDto;
import by.it_academy.jd2.finance.service.dto.user.UserOutDto;
import by.it_academy.jd2.finance.service.dto.user.UserSelfCreateDto;
import by.it_academy.jd2.finance.service.dto.user.UserUpdateDto;
import by.it_academy.jd2.finance.service.mapper.UserMapper;
import by.it_academy.jd2.finance.service.util.UserHolder;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserServiceWrapper implements IUserServiceWrapper {

    private final IUserService userService;
    private final UserMapper mapper;
    private final UserHolder userHolder;


    public UserServiceWrapper(IUserService userService, UserMapper mapper, UserHolder userHolder) {
        this.userService = userService;
        this.mapper = mapper;
        this.userHolder = userHolder;
    }

    @Override
    public void create(UserCreateDto createDto, String token) {
        userService.create(createDto, token);
    }

    @Override
    public void create(UserSelfCreateDto createDto) {
        userService.create(createDto);
    }

    @Override
    public UserOutDto getById(UUID id) {
        return mapper.toUserOutDto(userService.getById(id));
    }

    @Override
    public PageOf<UserOutDto> getAll(PageDto pageDto) {
        Page<UserOutDto> userPage = userService.getAll(pageDto)
                                               .map(mapper::toUserOutDto);
        return PageOf.convert(userPage);
    }

    @Override
    public void update(UserUpdateDto updateDto, UpdateCoordinate coordinate) {
        userService.update(updateDto, coordinate);
    }

    @Override
    public UserOutDto me() {
        return mapper.toUserOutDto(userService.getById(userHolder.getAuthUserId()));
    }
}
