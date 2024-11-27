package by.it_academy.jd2.finance.controller;

import by.it_academy.jd2.finance.service.IUserServiceWrapper;
import by.it_academy.jd2.finance.service.dto.UpdateCoordinate;
import by.it_academy.jd2.finance.service.dto.page.PageDto;
import by.it_academy.jd2.finance.service.dto.page.PageOf;
import by.it_academy.jd2.finance.service.dto.user.UserCreateDto;
import by.it_academy.jd2.finance.service.dto.user.UserOutDto;
import by.it_academy.jd2.finance.service.dto.user.UserUpdateDto;
import by.it_academy.jd2.finance.service.validation.UserExists;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.UUID;

@RestController
@RequestMapping("/users")
public class AdminController {

    private static final String PATH_VAR_ID_NAME = "uuid";
    private static final String PATH_VAR_DT_UPDATE = "dt_update";
    private final IUserServiceWrapper userServiceWrapper;

    public AdminController(IUserServiceWrapper userServiceWrapper) {
        this.userServiceWrapper = userServiceWrapper;
    }

    @PostMapping
    public ResponseEntity<HttpStatus> create(@RequestBody @Valid UserCreateDto createDto) {
        userServiceWrapper.create(createDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<UserOutDto> getById(@PathVariable(PATH_VAR_ID_NAME) @Valid @NotNull UUID id) {
        UserOutDto user = userServiceWrapper.getById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<PageOf<UserOutDto>> getAll(PageDto pageDto) {
        PageOf<UserOutDto> users = userServiceWrapper.getAll(pageDto);
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @PutMapping("/{uuid}/dt_update/{dt_update}")
    public ResponseEntity<HttpStatus> update(@PathVariable(PATH_VAR_ID_NAME) @UserExists UUID id,
                                             @PathVariable(PATH_VAR_DT_UPDATE) LocalDateTime updatedAt,
                                             @RequestBody @Valid UserUpdateDto updateDto) {
        userServiceWrapper.update(updateDto, UpdateCoordinate.builder()
                                                             .setId(id)
                                                             .setUpdatedAt(updatedAt)
                                                             .build());
        return new ResponseEntity<>(HttpStatus.OK);
    }
}