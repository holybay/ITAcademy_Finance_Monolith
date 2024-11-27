package by.it_academy.jd2.finance.service.dto.account;

import by.it_academy.jd2.finance.platform.util.CustomLocalDateTimeSerializer;
import by.it_academy.jd2.finance.repository.entity.account.EAccountType;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

public class AccountOutDto {

    @JsonProperty(value = "uuid", access = JsonProperty.Access.READ_ONLY)
    private UUID id;

    @JsonProperty(value = "dt_create", access = JsonProperty.Access.READ_ONLY)
    @JsonSerialize(using = CustomLocalDateTimeSerializer.class)
    private LocalDateTime createdAt;

    @JsonProperty(value = "dt_update", access = JsonProperty.Access.READ_ONLY)
    @JsonSerialize(using = CustomLocalDateTimeSerializer.class)
    private LocalDateTime updatedAt;

    private String title;

    private String description;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private BigDecimal balance;

    private EAccountType type;

    @JsonProperty("currency")
    private UUID currencyId;

    private AccountOutDto(UUID id, LocalDateTime createdAt, LocalDateTime updatedAt, String title, String description,
                          BigDecimal balance, EAccountType type, UUID currencyId) {
        this.id = id;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.title = title;
        this.description = description;
        this.balance = balance;
        this.type = type;
        this.currencyId = currencyId;
    }

    public static AccountOutDtoBuilder builder() {
        return new AccountOutDtoBuilder();
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

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public EAccountType getType() {
        return type;
    }

    public void setType(EAccountType type) {
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
        AccountOutDto that = (AccountOutDto) o;
        return Objects.equals(id, that.id) && Objects.equals(createdAt, that.createdAt)
                && Objects.equals(updatedAt, that.updatedAt) && Objects.equals(title, that.title)
                && Objects.equals(description, that.description) && Objects.equals(balance, that.balance)
                && type == that.type && Objects.equals(currencyId, that.currencyId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, createdAt, updatedAt, title, description, balance, type, currencyId);
    }

    @Override
    public String toString() {
        return "AccountOutDto{" +
                "id=" + id +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", balance=" + balance +
                ", type=" + type +
                ", currencyId=" + currencyId +
                '}';
    }

    public static class AccountOutDtoBuilder {

        private UUID id;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;
        private String title;
        private String description;
        private BigDecimal balance;
        private EAccountType type;
        private UUID currencyId;

        private AccountOutDtoBuilder() {
        }

        public AccountOutDto build() {
            return new AccountOutDto(id, createdAt, updatedAt, title, description, balance, type, currencyId);
        }

        public AccountOutDtoBuilder setId(UUID id) {
            this.id = id;
            return this;
        }

        public AccountOutDtoBuilder setCreatedAt(LocalDateTime createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public AccountOutDtoBuilder setUpdatedAt(LocalDateTime updatedAt) {
            this.updatedAt = updatedAt;
            return this;
        }

        public AccountOutDtoBuilder setTitle(String title) {
            this.title = title;
            return this;
        }

        public AccountOutDtoBuilder setDescription(String description) {
            this.description = description;
            return this;
        }

        public AccountOutDtoBuilder setBalance(BigDecimal balance) {
            this.balance = balance;
            return this;
        }

        public AccountOutDtoBuilder setType(EAccountType type) {
            this.type = type;
            return this;
        }

        public AccountOutDtoBuilder setCurrencyId(UUID currencyId) {
            this.currencyId = currencyId;
            return this;
        }
    }
}
