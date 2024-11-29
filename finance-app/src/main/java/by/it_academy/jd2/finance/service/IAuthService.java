package by.it_academy.jd2.finance.service;

import by.it_academy.jd2.finance.service.dto.user.UserLoginDto;
import by.it_academy.jd2.finance.service.dto.user.UserVerificationDto;

public interface IAuthService {

    String login(UserLoginDto loginDto);

    void verify(UserVerificationDto dto);
}
