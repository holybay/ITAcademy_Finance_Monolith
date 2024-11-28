package by.it_academy.jd2.finance.repository.entity.auditUnit;


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

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "audit_units")
public class AuditUnit {

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

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "text")
    private String text;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private EEssenceType type;

    @Column(name = "essence_type_id")
    private UUID essenceTypeId;

    public AuditUnit() {
    }

    private AuditUnit(UUID id, LocalDateTime createdAt, LocalDateTime updatedAt, User user, String text,
                      EEssenceType type, UUID essenceTypeId) {
        this.id = id;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.user = user;
        this.text = text;
        this.type = type;
        this.essenceTypeId = essenceTypeId;
    }

    public static AuditUnitBuilder builder() {
        return new AuditUnitBuilder();
    }

    @PrePersist
    public void prePersist() {
        id = UUID.randomUUID();
        createdAt = LocalDateTime.now();
        updatedAt = createdAt;
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

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public EEssenceType getType() {
        return type;
    }

    public void setType(EEssenceType type) {
        this.type = type;
    }

    public UUID getEssenceTypeId() {
        return essenceTypeId;
    }

    public void setEssenceTypeId(UUID essenceTypeId) {
        this.essenceTypeId = essenceTypeId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AuditUnit auditUnit = (AuditUnit) o;
        return Objects.equals(id, auditUnit.id) && Objects.equals(createdAt, auditUnit.createdAt)
                && Objects.equals(updatedAt, auditUnit.updatedAt) && Objects.equals(user, auditUnit.user)
                && Objects.equals(text, auditUnit.text) && type == auditUnit.type
                && Objects.equals(essenceTypeId, auditUnit.essenceTypeId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, createdAt, updatedAt, user, text, type, essenceTypeId);
    }

    @Override
    public String toString() {
        return "AuditUnit{" +
                "id=" + id +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", user=" + user +
                ", text='" + text + '\'' +
                ", type=" + type +
                ", essenceTypeId=" + essenceTypeId +
                '}';
    }

    public static class AuditUnitBuilder {

        private UUID id;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;
        private User user;
        private String text;
        private EEssenceType type;
        private UUID essenceTypeId;

        private AuditUnitBuilder() {
        }

        public AuditUnit build() {
            return new AuditUnit(id, createdAt, updatedAt, user, text, type, essenceTypeId);
        }

        public AuditUnitBuilder setId(UUID id) {
            this.id = id;
            return this;
        }

        public AuditUnitBuilder setCreatedAt(LocalDateTime createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public AuditUnitBuilder setUpdatedAt(LocalDateTime updatedAt) {
            this.updatedAt = updatedAt;
            return this;
        }

        public AuditUnitBuilder setUser(User user) {
            this.user = user;
            return this;
        }

        public AuditUnitBuilder setText(String text) {
            this.text = text;
            return this;
        }

        public AuditUnitBuilder setType(EEssenceType type) {
            this.type = type;
            return this;
        }

        public AuditUnitBuilder setEssenceTypeId(UUID essenceTypeId) {
            this.essenceTypeId = essenceTypeId;
            return this;
        }
    }
}
