package by.it_academy.jd2.finance.platform.exception.dto;


import by.it_academy.jd2.finance.platform.exception.model.EErrorType;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class AppExceptionDtoStructured {

    private final EErrorType logref;
    private final List<StructuredExceptionDto> errors;

    public AppExceptionDtoStructured() {
        this.logref = EErrorType.STRUCTURED_ERROR;
        this.errors = new ArrayList<>();
    }

    public AppExceptionDtoStructured(List<StructuredExceptionDto> errors) {
        this.logref = EErrorType.STRUCTURED_ERROR;
        this.errors = errors;
    }

    public EErrorType getLogref() {
        return logref;
    }

    public List<StructuredExceptionDto> getErrors() {
        return errors;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AppExceptionDtoStructured that = (AppExceptionDtoStructured) o;
        return logref == that.logref && Objects.equals(errors, that.errors);
    }

    @Override
    public int hashCode() {
        return Objects.hash(logref, errors);
    }

    @Override
    public String toString() {
        return "AppExceptionDtoStructured{" +
                "logref=" + logref +
                ", errors=" + errors +
                '}';
    }
}
