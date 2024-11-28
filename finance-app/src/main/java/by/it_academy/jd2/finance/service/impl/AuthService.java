package by.it_academy.jd2.finance.service.impl;

import by.it_academy.jd2.finance.platform.exception.AppAuthException;
import by.it_academy.jd2.finance.repository.entity.auditUnit.EEssenceType;
import by.it_academy.jd2.finance.repository.entity.user.EUserStatus;
import by.it_academy.jd2.finance.repository.entity.user.User;
import by.it_academy.jd2.finance.service.IAuditService;
import by.it_academy.jd2.finance.service.IAuthService;
import by.it_academy.jd2.finance.service.IUserService;
import by.it_academy.jd2.finance.service.dto.auditUnit.AuditUnitCreateDto;
import by.it_academy.jd2.finance.service.dto.user.UserLoginDto;
import by.it_academy.jd2.finance.service.util.JwtTokenHandler;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService implements IAuthService {

    private final IUserService userService;
    private final PasswordEncoder encoder;
    private final JwtTokenHandler tokenHandler;
    private final IAuditService auditService;

    public AuthService(IUserService userService, PasswordEncoder encoder, JwtTokenHandler tokenHandler, IAuditService auditService) {
        this.userService = userService;
        this.encoder = encoder;
        this.tokenHandler = tokenHandler;
        this.auditService = auditService;
    }

    @Override
    public String login(UserLoginDto loginDto) {
        User user = userService.getByMail(loginDto.getMail());

        if (user.getStatus() == EUserStatus.DEACTIVATED || !encoder.matches(loginDto.getPassword(), user.getPassword())) {
            throw new AppAuthException("Incorrect mail or password!");
        }
        auditService.create(AuditUnitCreateDto.builder()
                                              .setUserId(user.getId())
                                              .setText("User logged in!")
                                              .setType(EEssenceType.USER)
                                              .setEssenceTypeId(user.getId())
                                              .build());
        return tokenHandler.generateToken(user);
    }
}
