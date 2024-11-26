package by.it_academy.jd2.finance.platform.exception.dto;


import by.it_academy.jd2.finance.platform.exception.model.EErrorType;

import java.util.Objects;

public class AppExceptionDtoSimple {

    private final EErrorType logref;
    private String message;

    public AppExceptionDtoSimple() {
        this.logref = EErrorType.ERROR;
    }

    public AppExceptionDtoSimple(String message) {
        this();
        this.message = message;
    }

    public EErrorType getLogref() {
        return logref;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AppExceptionDtoSimple that = (AppExceptionDtoSimple) o;
        return logref == that.logref && Objects.equals(message, that.message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(logref, message);
    }

    @Override
    public String toString() {
        return "AppExceptionDtoSimple{" +
                "logref=" + logref +
                ", message='" + message + '\'' +
                '}';
    }
}
