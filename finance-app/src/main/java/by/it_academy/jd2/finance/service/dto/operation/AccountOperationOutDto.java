package by.it_academy.jd2.finance.service.dto.operation;

import by.it_academy.jd2.finance.platform.util.CustomLocalDateTimeSerializer;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

public class AccountOperationOutDto {

    @JsonProperty(value = "uuid", access = JsonProperty.Access.READ_ONLY)
    private UUID id;

    @JsonProperty(value = "dt_create", access = JsonProperty.Access.READ_ONLY)
    @JsonSerialize(using = CustomLocalDateTimeSerializer.class)
    private LocalDateTime createdAt;

    @JsonProperty(value = "dt_update", access = JsonProperty.Access.READ_ONLY)
    @JsonSerialize(using = CustomLocalDateTimeSerializer.class)
    private LocalDateTime updatedAt;

    @JsonSerialize(using = CustomLocalDateTimeSerializer.class)
    private LocalDateTime date;

    private String description;

    @JsonProperty("category")
    private UUID categoryId;

    private BigDecimal value;

    @JsonProperty("currency")
    private UUID currencyId;

    private AccountOperationOutDto(UUID id, LocalDateTime createdAt, LocalDateTime updatedAt, LocalDateTime date,
                                   String description, UUID categoryId, BigDecimal value, UUID currencyId) {
        this.id = id;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.date = date;
        this.description = description;
        this.categoryId = categoryId;
        this.value = value;
        this.currencyId = currencyId;
    }

    public static AccountOperationOutDtoBuilder builder() {
        return new AccountOperationOutDtoBuilder();
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

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
        AccountOperationOutDto that = (AccountOperationOutDto) o;
        return Objects.equals(id, that.id) && Objects.equals(createdAt, that.createdAt)
                && Objects.equals(updatedAt, that.updatedAt) && Objects.equals(date, that.date)
                && Objects.equals(description, that.description) && Objects.equals(categoryId, that.categoryId)
                && Objects.equals(value, that.value) && Objects.equals(currencyId, that.currencyId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, createdAt, updatedAt, date, description, categoryId, value, currencyId);
    }

    @Override
    public String toString() {
        return "AccountOperationOutDto{" +
                "id=" + id +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", date=" + date +
                ", description='" + description + '\'' +
                ", categoryId=" + categoryId +
                ", value=" + value +
                ", currencyId=" + currencyId +
                '}';
    }

    public static class AccountOperationOutDtoBuilder {

        private UUID id;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;
        private LocalDateTime date;
        private String description;
        private UUID categoryId;
        private BigDecimal value;
        private UUID currencyId;

        private AccountOperationOutDtoBuilder() {
        }

        public AccountOperationOutDto build() {
            return new AccountOperationOutDto(id, createdAt, updatedAt, date, description, categoryId, value, currencyId);
        }

        public AccountOperationOutDtoBuilder setId(UUID id) {
            this.id = id;
            return this;
        }

        public AccountOperationOutDtoBuilder setCreatedAt(LocalDateTime createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public AccountOperationOutDtoBuilder setUpdatedAt(LocalDateTime updatedAt) {
            this.updatedAt = updatedAt;
            return this;
        }

        public AccountOperationOutDtoBuilder setDate(LocalDateTime date) {
            this.date = date;
            return this;
        }

        public AccountOperationOutDtoBuilder setDescription(String description) {
            this.description = description;
            return this;
        }

        public AccountOperationOutDtoBuilder setCategoryId(UUID categoryId) {
            this.categoryId = categoryId;
            return this;
        }

        public AccountOperationOutDtoBuilder setValue(BigDecimal value) {
            this.value = value;
            return this;
        }

        public AccountOperationOutDtoBuilder setCurrencyId(UUID currencyId) {
            this.currencyId = currencyId;
            return this;
        }
    }
}
