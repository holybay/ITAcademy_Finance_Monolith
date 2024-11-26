package by.it_academy.jd2.finance.platform.util;


import by.it_academy.jd2.finance.platform.exception.ApplicationStructuredException;
import by.it_academy.jd2.finance.platform.exception.dto.AppExceptionDtoSimple;
import by.it_academy.jd2.finance.platform.exception.dto.StructuredExceptionDto;

import java.util.ArrayList;
import java.util.List;

public class ExceptionDtoUtil {

    public static List<AppExceptionDtoSimple> getAppExceptionSimpleDto(Exception e) {
        List<AppExceptionDtoSimple> errors = new ArrayList<>();
        errors.add(new AppExceptionDtoSimple(e.getMessage()));
        return errors;
    }

    public static List<AppExceptionDtoSimple> getAppExceptionSimpleDto(String message) {
        List<AppExceptionDtoSimple> errors = new ArrayList<>();
        errors.add(new AppExceptionDtoSimple(message));
        return errors;
    }

    public static <T extends ApplicationStructuredException> List<StructuredExceptionDto> convertToStructuredExceptionDtoList(
            List<T> errors) {
        return errors.stream()
                     .map(e -> new StructuredExceptionDto(e.getField(), e.getMessage()))
                     .toList();
    }
}
