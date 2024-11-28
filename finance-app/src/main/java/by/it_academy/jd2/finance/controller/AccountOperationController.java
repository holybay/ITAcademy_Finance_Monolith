package by.it_academy.jd2.finance.controller;

import by.it_academy.jd2.finance.service.IAccountOperationService;
import by.it_academy.jd2.finance.service.dto.UpdateCoordinate;
import by.it_academy.jd2.finance.service.dto.operation.AccountOperationCoordinate;
import by.it_academy.jd2.finance.service.dto.operation.AccountOperationCreateDto;
import by.it_academy.jd2.finance.service.dto.operation.AccountOperationOutDto;
import by.it_academy.jd2.finance.service.dto.operation.AccountOperationUpdateDto;
import by.it_academy.jd2.finance.service.dto.page.PageDto;
import by.it_academy.jd2.finance.service.dto.page.PageOf;
import by.it_academy.jd2.finance.service.util.JwtTokenHandler;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
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
@RequestMapping("/account/{uuid}/operation")
public class AccountOperationController {

    private static final String PATH_VAR_ACCOUNT_ID = "uuid";
    private static final String PATH_VAR_DT_UPDATE = "dt_update";
    private static final String AUTH_HEADER = "Authorization";
    private static final String PATH_VAR_OPERATION_ID = "uuid_operation";
    private final IAccountOperationService operationService;

    public AccountOperationController(IAccountOperationService operationService) {
        this.operationService = operationService;
    }

    @PostMapping
    public ResponseEntity<HttpStatus> create(@PathVariable(PATH_VAR_ACCOUNT_ID) UUID accountId,
                                             @RequestBody @Valid AccountOperationCreateDto createDto,
                                             @RequestHeader(AUTH_HEADER) String header) {
        operationService.create(accountId, createDto, JwtTokenHandler.getTokenFromHeader(header));
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<PageOf<AccountOperationOutDto>> getAll(@PathVariable(PATH_VAR_ACCOUNT_ID) UUID accountId,
                                                                 @Valid PageDto pageDto,
                                                                 @RequestHeader(AUTH_HEADER) String header) {
        PageOf<AccountOperationOutDto> users = operationService.getAll(accountId, pageDto,
                JwtTokenHandler.getTokenFromHeader(header));
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @PutMapping("/{uuid_operation}/dt_update/{dt_update}")
    public ResponseEntity<HttpStatus> update(@PathVariable(PATH_VAR_ACCOUNT_ID) UUID accountId,
                                             @PathVariable(PATH_VAR_DT_UPDATE) LocalDateTime updatedAt,
                                             @PathVariable(PATH_VAR_OPERATION_ID) UUID operationId,
                                             @RequestBody @Valid AccountOperationUpdateDto updateDto,
                                             @RequestHeader(AUTH_HEADER) String header) {
        operationService.update(updateDto, UpdateCoordinate.builder()
                                                           .setId(operationId)
                                                           .setUpdatedAt(updatedAt)
                                                           .build(), accountId, JwtTokenHandler.getTokenFromHeader(header));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{uuid_operation}/dt_update/{dt_update}")
    public ResponseEntity<HttpStatus> delete(@PathVariable(PATH_VAR_ACCOUNT_ID) UUID accountId,
                                             @PathVariable(PATH_VAR_DT_UPDATE) LocalDateTime updatedAt,
                                             @PathVariable(PATH_VAR_OPERATION_ID) UUID operationId,
                                             @RequestHeader(AUTH_HEADER) String header) {
        operationService.delete(UpdateCoordinate.builder()
                                                .setId(operationId)
                                                .setUpdatedAt(updatedAt)
                                                .build(), accountId, JwtTokenHandler.getTokenFromHeader(header));
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
