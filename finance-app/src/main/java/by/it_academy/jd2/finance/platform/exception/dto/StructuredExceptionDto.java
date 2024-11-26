package by.it_academy.jd2.finance.platform.exception.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Map;
import java.util.Objects;

@JsonPropertyOrder({"field", "message"})
public class StructuredExceptionDto {

    private static final String KEY_FIELD = "field";
    private static final String KEY_MESSAGE = "message";
    private final String field;
    private final String message;

    public StructuredExceptionDto(String field, String message) {
        this.field = field;
        this.message = message;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StructuredExceptionDto that = (StructuredExceptionDto) o;
        return Objects.equals(field, that.field) && Objects.equals(message, that.message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(field, message);
    }

    @Override
    public String toString() {
        return "StructuredExceptionDto{" +
                "field='" + field + '\'' +
                ", message='" + message + '\'' +
                '}';
    }

    @JsonValue
    public Map<String, String> getBriefInfo() {
        return Map.of(KEY_FIELD, field, KEY_MESSAGE, message);
    }
}
