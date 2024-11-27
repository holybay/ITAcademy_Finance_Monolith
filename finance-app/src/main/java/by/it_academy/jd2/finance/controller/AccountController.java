package by.it_academy.jd2.finance.controller;

import by.it_academy.jd2.finance.service.IAccountService;
import by.it_academy.jd2.finance.service.dto.UpdateCoordinate;
import by.it_academy.jd2.finance.service.dto.account.AccountCreateDto;
import by.it_academy.jd2.finance.service.dto.account.AccountOutDto;
import by.it_academy.jd2.finance.service.dto.account.AccountUpdateDto;
import by.it_academy.jd2.finance.service.dto.page.PageDto;
import by.it_academy.jd2.finance.service.dto.page.PageOf;
import by.it_academy.jd2.finance.service.util.JwtTokenHandler;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.UUID;

@RestController
@RequestMapping("/account")
public class AccountController {

    public static final String PATH_VAR_ID_NAME = "uuid";
    public static final String PATH_VAR_DT_UPDATE = "dt_update";
    public static final String AUTH_HEADER = "Authorization";
    private final IAccountService accountService;

    public AccountController(IAccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping
    public ResponseEntity<HttpStatus> create(@RequestBody @Valid AccountCreateDto createDto,
                                             @RequestHeader(AUTH_HEADER) String header) {
        accountService.create(createDto, JwtTokenHandler.getTokenFromHeader(header));
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<AccountOutDto> getById(@PathVariable(PATH_VAR_ID_NAME) UUID id,
                                                 @RequestHeader(AUTH_HEADER) String header) {
        AccountOutDto user = accountService.getById(id, JwtTokenHandler.getTokenFromHeader(header));
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<PageOf<AccountOutDto>> getAll(PageDto pageDto,
                                                        @RequestHeader(AUTH_HEADER) String header) {
        PageOf<AccountOutDto> users = accountService.getAll(pageDto, JwtTokenHandler.getTokenFromHeader(header));
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @PutMapping("/{uuid}/dt_update/{dt_update}")
    public ResponseEntity<HttpStatus> update(@PathVariable(PATH_VAR_ID_NAME) UUID id,
                                             @PathVariable(PATH_VAR_DT_UPDATE) LocalDateTime updatedAt,
                                             @RequestBody @Valid AccountUpdateDto updateDto,
                                             @RequestHeader(AUTH_HEADER) String header) {
        accountService.update(updateDto, UpdateCoordinate.builder()
                                                         .setId(id)
                                                         .setUpdatedAt(updatedAt)
                                                         .build(),
                JwtTokenHandler.getTokenFromHeader(header));
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
