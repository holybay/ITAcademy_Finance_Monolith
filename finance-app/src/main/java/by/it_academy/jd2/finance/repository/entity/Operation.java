package by.it_academy.jd2.finance.repository.entity;

import by.it_academy.jd2.finance.repository.entity.account.Account;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.persistence.Version;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "account_operations")
public class Operation {

    @Id
    @Column(name = "id", updatable = false)
    private UUID id;

    @Column(name = "created_at", updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    @Version
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime updatedAt;

    @Column(name = "operation_date")
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime date;

    @Column(name = "description")
    private String description;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id")
    private Category category;

    @Column(name = "amount")
    private BigDecimal value;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "currency_id")
    private Currency currency;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "account_id")
    private Account account;

    public Operation() {
    }

    public Operation(UUID id, LocalDateTime createdAt, LocalDateTime updatedAt, LocalDateTime date,
                     String description, Category category, BigDecimal value, Currency currency,
                     Account account) {
        this.id = id;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.date = date;
        this.description = description;
        this.category = category;
        this.value = value;
        this.currency = currency;
        this.account = account;
    }

    public static OperationBuilder builder() {
        return new OperationBuilder();
    }

    @PrePersist
    public void prePersist() {
        this.id = UUID.randomUUID();
        this.createdAt = LocalDateTime.now();
        this.updatedAt = this.createdAt;
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

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Operation operation = (Operation) o;
        return Objects.equals(id, operation.id) && Objects.equals(createdAt, operation.createdAt)
                && Objects.equals(updatedAt, operation.updatedAt) && Objects.equals(date, operation.date)
                && Objects.equals(description, operation.description) && Objects.equals(category, operation.category)
                && Objects.equals(value, operation.value) && Objects.equals(currency, operation.currency)
                && Objects.equals(account, operation.account);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, createdAt, updatedAt, date, description, category, value, currency, account);
    }

    @Override
    public String toString() {
        return "Operation{" +
                "id=" + id +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", date=" + date +
                ", description='" + description + '\'' +
                ", category=" + category +
                ", value=" + value +
                ", currency=" + currency +
                ", account=" + account +
                '}';
    }

    public static class OperationBuilder {

        private UUID id;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;
        private LocalDateTime date;
        private String description;
        private Category category;
        private BigDecimal value;
        private Currency currency;
        private Account account;

        private OperationBuilder() {
        }

        public OperationBuilder setId(UUID id) {
            this.id = id;
            return this;
        }

        public OperationBuilder setCreatedAt(LocalDateTime createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public OperationBuilder setUpdatedAt(LocalDateTime updatedAt) {
            this.updatedAt = updatedAt;
            return this;
        }

        public OperationBuilder setDate(LocalDateTime date) {
            this.date = date;
            return this;
        }

        public OperationBuilder setDescription(String description) {
            this.description = description;
            return this;
        }

        public OperationBuilder setCategory(Category category) {
            this.category = category;
            return this;
        }

        public OperationBuilder setValue(BigDecimal value) {
            this.value = value;
            return this;
        }

        public OperationBuilder setCurrency(Currency currency) {
            this.currency = currency;
            return this;
        }

        public OperationBuilder setAccount(Account account) {
            this.account = account;
            return this;
        }

        public Operation build() {
            return new Operation(id, createdAt, updatedAt, date, description, category, value, currency, account);
        }
    }
}
