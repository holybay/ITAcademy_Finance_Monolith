package by.it_academy.jd2.finance.service.dto.operation;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

public class AccountOperationUpdateDto {

    @NotNull(message = "Operation date can't be null!")
    private LocalDateTime date;

    @NotBlank(message = "Operation description can't be blank!")
    private String description;

    @JsonProperty("category")
    @NotNull(message = "Category id can't be null!")
    private UUID categoryId;

    @NotNull(message = "Operation value can't be null!")
    @Digits(integer = 13, fraction = 2, message = "Incorrect operation value precision!")
    private BigDecimal value;

    @JsonProperty("currency")
    @NotNull(message = "Currency id can't be null!")
    private UUID currencyId;

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public UUID getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(UUID categoryId) {
        this.categoryId = categoryId;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public UUID getCurrencyId() {
        return currencyId;
    }

    public void setCurrencyId(UUID currencyId) {
        this.currencyId = currencyId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AccountOperationUpdateDto that = (AccountOperationUpdateDto) o;
        return Objects.equals(date, that.date) && Objects.equals(description, that.description)
                && Objects.equals(categoryId, that.categoryId) && Objects.equals(value, that.value)
                && Objects.equals(currencyId, that.currencyId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(date, description, categoryId, value, currencyId);
    }

    @Override
    public String toString() {
        return "AccountOperationUpdateDto{" +
                "date=" + date +
                ", description='" + description + '\'' +
                ", categoryId=" + categoryId +
                ", value=" + value +
                ", currencyId=" + currencyId +
                '}';
    }
}
