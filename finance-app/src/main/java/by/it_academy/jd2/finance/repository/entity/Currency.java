package by.it_academy.jd2.finance.repository.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.persistence.Version;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "currencies")
public class Currency {

    public static final String USD = "USD";
    public static final String EUR = "EUR";
    public static final String RUB = "RUB";
    public static final String BYN = "BYN";

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

    public Currency() {
    }

    private Currency(UUID id, LocalDateTime createdAt, LocalDateTime updatedAt, String title, String description) {
        this.id = id;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.title = title;
        this.description = description;
    }

    public static CurrencyBuilder builder() {
        return new CurrencyBuilder();
    }

    @PrePersist
    public void prePersist() {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Currency currency = (Currency) o;
        return Objects.equals(id, currency.id) && Objects.equals(createdAt, currency.createdAt) && Objects.equals(updatedAt, currency.updatedAt) && Objects.equals(title, currency.title) && Objects.equals(description, currency.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, createdAt, updatedAt, title, description);
    }

    @Override
    public String toString() {
        return "Currency{" +
                "id=" + id +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    public static class CurrencyBuilder {
        private UUID id;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;
        private String title;
        private String description;

        private CurrencyBuilder() {
        }

        public Currency build() {
            return new Currency(id, createdAt, updatedAt, title, description);
        }

        public CurrencyBuilder setId(UUID id) {
            this.id = id;
            return this;
        }

        public CurrencyBuilder setCreatedAt(LocalDateTime createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public CurrencyBuilder setUpdatedAt(LocalDateTime updatedAt) {
            this.updatedAt = updatedAt;
            return this;
        }

        public CurrencyBuilder setTitle(String title) {
            this.title = title;
            return this;
        }

        public CurrencyBuilder setDescription(String description) {
            this.description = description;
            return this;
        }
    }
}
