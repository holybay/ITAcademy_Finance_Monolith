package by.it_academy.jd2.finance.controller;

import by.it_academy.jd2.finance.service.IAuthService;
import by.it_academy.jd2.finance.service.IUserServiceWrapper;
import by.it_academy.jd2.finance.service.dto.user.UserLoginDto;
import by.it_academy.jd2.finance.service.dto.user.UserOutDto;
import by.it_academy.jd2.finance.service.dto.user.UserSelfCreateDto;
import by.it_academy.jd2.finance.service.dto.user.UserVerificationDto;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cabinet")
public class UserController {

    private final IUserServiceWrapper userServiceWrapper;
    private final IAuthService authService;

    public UserController(IUserServiceWrapper userServiceWrapper, IAuthService authService) {
        this.userServiceWrapper = userServiceWrapper;
        this.authService = authService;
    }

    @PostMapping("/registration")
    public ResponseEntity<HttpStatus> create(@RequestBody @Valid UserSelfCreateDto createDto) {
        userServiceWrapper.create(createDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/verification")
    public ResponseEntity<HttpStatus> getById(@Valid UserVerificationDto verificationDto) {
//        UserOutDto user = userService.getById();
        return new ResponseEntity<>(HttpStatus.OK); //TODO verify on the IAuthService
    }

    @PostMapping("/login")
    public ResponseEntity<String> create(@RequestBody @Valid UserLoginDto loginDto) {
        return new ResponseEntity<>(authService.login(loginDto), HttpStatus.OK);
    }

    @GetMapping("/me")
    public ResponseEntity<UserOutDto> getMe() {
        return new ResponseEntity<>(userServiceWrapper.me(), HttpStatus.OK);
    }
}
