package by.it_academy.jd2.finance.service;

import by.it_academy.jd2.finance.service.dto.user.UserLoginDto;

public interface IAuthService {

    String login(UserLoginDto loginDto);
}
