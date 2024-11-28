package by.it_academy.jd2.finance.controller.advice;

import by.it_academy.jd2.finance.platform.exception.AppAuthException;
import by.it_academy.jd2.finance.platform.exception.ValidationException;
import by.it_academy.jd2.finance.platform.exception.dto.AppExceptionDtoSimple;
import by.it_academy.jd2.finance.platform.exception.dto.AppExceptionDtoStructured;
import by.it_academy.jd2.finance.platform.exception.dto.StructuredExceptionDto;
import by.it_academy.jd2.finance.platform.util.ExceptionDtoUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.HandlerMethodValidationException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.util.List;
import java.util.NoSuchElementException;

@RestControllerAdvice
public class GlobalHandler {

    @ExceptionHandler
    public ResponseEntity<List<AppExceptionDtoSimple>> handle(Exception e) {
        return new ResponseEntity<>(ExceptionDtoUtil.getAppExceptionSimpleDto(e), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler
    public ResponseEntity<List<AppExceptionDtoSimple>> handle(NoSuchElementException e) {
        return new ResponseEntity<>(ExceptionDtoUtil.getAppExceptionSimpleDto(e), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<List<AppExceptionDtoSimple>> handle(IllegalArgumentException e) {
        return new ResponseEntity<>(ExceptionDtoUtil.getAppExceptionSimpleDto(e), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<List<AppExceptionDtoSimple>> handle(MethodArgumentTypeMismatchException e) {
        return new ResponseEntity<>(ExceptionDtoUtil.getAppExceptionSimpleDto(e), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<List<AppExceptionDtoSimple>> handle(AppAuthException e) {
        return new ResponseEntity<>(ExceptionDtoUtil.getAppExceptionSimpleDto(e), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<List<AppExceptionDtoSimple>> handle(NoResourceFoundException e) {
        return new ResponseEntity<>(ExceptionDtoUtil.getAppExceptionSimpleDto("Check URL or the provided WEB arguments!"),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<AppExceptionDtoStructured> handle(ValidationException e) {
        AppExceptionDtoStructured out = new AppExceptionDtoStructured(
                ExceptionDtoUtil.convertToStructuredExceptionDtoList(e.getErrors()));
        return new ResponseEntity<>(out, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<AppExceptionDtoStructured> handle(HandlerMethodValidationException e) {
        List<StructuredExceptionDto> errorList = e.getAllValidationResults().stream()
                                                  .map(er -> new StructuredExceptionDto(
                                                          er.getMethodParameter().getParameterName(),
                                                          er.getResolvableErrors().get(0).getDefaultMessage()))
                                                  .toList();
        AppExceptionDtoStructured out = new AppExceptionDtoStructured(errorList);
        return new ResponseEntity<>(out, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<AppExceptionDtoStructured> handle(MethodArgumentNotValidException e) {
        List<StructuredExceptionDto> errorList = e.getBindingResult().getFieldErrors().stream()
                                                  .map(er -> new StructuredExceptionDto(
                                                          er.getField(),
                                                          er.getDefaultMessage()))
                                                  .toList();
        AppExceptionDtoStructured out = new AppExceptionDtoStructured(errorList);
        return new ResponseEntity<>(out, HttpStatus.BAD_REQUEST);
    }
}
