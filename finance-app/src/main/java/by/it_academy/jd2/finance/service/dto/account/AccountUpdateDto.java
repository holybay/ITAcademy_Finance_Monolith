package by.it_academy.jd2.finance.service.dto.account;

import by.it_academy.jd2.finance.repository.entity.account.EAccountType;
import by.it_academy.jd2.finance.service.validation.EnumValidator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Objects;
import java.util.UUID;

public class AccountUpdateDto {

    @NotBlank(message = "Account title can't be blank!")
    private String title;

    @NotBlank(message = "Account description can't be blank!")
    private String description;

    @NotBlank(message = "Account type can't be blank!")
    @EnumValidator(enumClass = EAccountType.class)
    private String type;

    @JsonProperty("currency")
    @NotNull(message = "Currency id can't be null!")
    private UUID currencyId;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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
        AccountUpdateDto that = (AccountUpdateDto) o;
        return Objects.equals(title, that.title) && Objects.equals(description, that.description)
                && Objects.equals(type, that.type) && Objects.equals(currencyId, that.currencyId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, description, type, currencyId);
    }

    @Override
    public String toString() {
        return "AccountUpdateDto{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", type='" + type + '\'' +
                ", currencyId=" + currencyId +
                '}';
    }
}
