package by.it_academy.jd2.finance.repository.entity;


import by.it_academy.jd2.finance.repository.entity.user.User;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.persistence.Version;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "verification_units")
public class VerificationUnit {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", updatable = false)
    private UUID id;

    @Column(name = "created_at", updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime createdAt;

    @Version
    @Column(name = "updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime updatedAt;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "code")
    private Integer code;

    public VerificationUnit() {
    }

    public VerificationUnit(UUID id, LocalDateTime createdAt, LocalDateTime updatedAt, User user, Integer code) {
        this.id = id;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.user = user;
        this.code = code;
    }

    public static VerificationUnitBuilder builder() {
        return new VerificationUnitBuilder();
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VerificationUnit that = (VerificationUnit) o;
        return Objects.equals(id, that.id) && Objects.equals(createdAt, that.createdAt)
                && Objects.equals(updatedAt, that.updatedAt) && Objects.equals(user, that.user)
                && Objects.equals(code, that.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, createdAt, updatedAt, user, code);
    }

    @Override
    public String toString() {
        return "VerificationUnit{" +
                "id=" + id +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", user=" + user +
                ", code=" + code +
                '}';
    }

    public static class VerificationUnitBuilder {

        private UUID id;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;
        private User user;
        private Integer code;

        private VerificationUnitBuilder() {
        }

        public VerificationUnit build() {
            return new VerificationUnit(id, createdAt, updatedAt, user, code);
        }

        public VerificationUnitBuilder setId(UUID id) {
            this.id = id;
            return this;
        }

        public VerificationUnitBuilder setCreatedAt(LocalDateTime createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public VerificationUnitBuilder setUpdatedAt(LocalDateTime updatedAt) {
            this.updatedAt = updatedAt;
            return this;
        }

        public VerificationUnitBuilder setUser(User user) {
            this.user = user;
            return this;
        }

        public VerificationUnitBuilder setCode(Integer code) {
            this.code = code;
            return this;
        }
    }
}
