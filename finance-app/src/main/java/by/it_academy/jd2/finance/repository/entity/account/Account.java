package by.it_academy.jd2.finance.repository.entity.account;

import by.it_academy.jd2.finance.repository.entity.Currency;
import by.it_academy.jd2.finance.repository.entity.user.User;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
@Table(name = "accounts")
public class Account {

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

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "balance")
    private BigDecimal balance;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private EAccountType type;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "currency_id")
    private Currency currency;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    public Account() {
    }

    private Account(UUID id, LocalDateTime createdAt, LocalDateTime updatedAt, String title, String description,
                    BigDecimal balance, EAccountType type, Currency currency, User user) {
        this.id = id;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.title = title;
        this.description = description;
        this.balance = balance;
        this.type = type;
        this.currency = currency;
        this.user = user;
    }

    public static AccountBuilder builder() {
        return new AccountBuilder();
    }

    @PrePersist
    public void prePersist() {
        this.id = UUID.randomUUID();
        this.createdAt = LocalDateTime.now();
        this.updatedAt = this.createdAt;
        this.balance = BigDecimal.ZERO;
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

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return Objects.equals(id, account.id) && Objects.equals(createdAt, account.createdAt)
                && Objects.equals(updatedAt, account.updatedAt) && Objects.equals(title, account.title)
                && Objects.equals(description, account.description) && Objects.equals(balance, account.balance)
                && type == account.type && Objects.equals(currency, account.currency)
                && Objects.equals(user, account.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, createdAt, updatedAt, title, description, balance, type, currency, user);
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", balance=" + balance +
                ", type=" + type +
                ", currency=" + currency +
                ", user=" + user +
                '}';
    }

    public static class AccountBuilder {

        private UUID id;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;
        private String title;
        private String description;
        private BigDecimal balance;
        private EAccountType type;
        private Currency currency;
        private User user;

        private AccountBuilder() {
        }

        public AccountBuilder setId(UUID id) {
            this.id = id;
            return this;
        }

        public AccountBuilder setCreatedAt(LocalDateTime createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public AccountBuilder setUpdatedAt(LocalDateTime updatedAt) {
            this.updatedAt = updatedAt;
            return this;
        }

        public AccountBuilder setTitle(String title) {
            this.title = title;
            return this;
        }

        public AccountBuilder setDescription(String description) {
            this.description = description;
            return this;
        }

        public AccountBuilder setBalance(BigDecimal balance) {
            this.balance = balance;
            return this;
        }

        public AccountBuilder setType(EAccountType type) {
            this.type = type;
            return this;
        }

        public AccountBuilder setCurrency(Currency currency) {
            this.currency = currency;
            return this;
        }

        public AccountBuilder setUser(User user) {
            this.user = user;
            return this;
        }

        public Account build() {
            return new Account(id, createdAt, updatedAt, title, description, balance, type, currency, user);
        }
    }
}
