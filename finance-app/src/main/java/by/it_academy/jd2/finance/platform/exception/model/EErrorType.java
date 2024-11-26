package by.it_academy.jd2.finance.platform.exception.model;

import com.fasterxml.jackson.annotation.JsonValue;

public enum EErrorType {

    ERROR("error"),
    STRUCTURED_ERROR("structured_error");

    private final String name;

    EErrorType(String name) {
        this.name = name;
    }

    @JsonValue
    public String getValue() {
        return name;
    }

}
