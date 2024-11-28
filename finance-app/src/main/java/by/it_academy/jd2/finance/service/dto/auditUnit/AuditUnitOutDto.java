package by.it_academy.jd2.finance.service.dto.auditUnit;


import by.it_academy.jd2.finance.platform.util.CustomLocalDateTimeSerializer;
import by.it_academy.jd2.finance.repository.entity.auditUnit.EEssenceType;
import by.it_academy.jd2.finance.service.dto.user.UserAuditOutDto;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

public class AuditUnitOutDto {

    @JsonProperty(value = "uuid", access = JsonProperty.Access.READ_ONLY)
    private UUID id;

    @JsonProperty(value = "dt_create", access = JsonProperty.Access.READ_ONLY)
    @JsonSerialize(using = CustomLocalDateTimeSerializer.class)
    private LocalDateTime createdAt;

    @JsonProperty(value = "dt_update", access = JsonProperty.Access.READ_ONLY)
    @JsonSerialize(using = CustomLocalDateTimeSerializer.class)
    private LocalDateTime updatedAt;

    private UserAuditOutDto user;

    private String text;

    private EEssenceType type;

    @JsonProperty("id")
    private UUID essenceTypeId;

    public AuditUnitOutDto() {
    }

    private AuditUnitOutDto(UUID id, LocalDateTime createdAt, LocalDateTime updatedAt, UserAuditOutDto user, String text,
                            EEssenceType type, UUID essenceTypeId) {
        this.id = id;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.user = user;
        this.text = text;
        this.type = type;
        this.essenceTypeId = essenceTypeId;
    }

    public static AuditUnitOutDtoBuilder builder() {
        return new AuditUnitOutDtoBuilder();
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

    public UserAuditOutDto getUser() {
        return user;
    }

    public void setUser(UserAuditOutDto user) {
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
        AuditUnitOutDto auditUnitOutDto = (AuditUnitOutDto) o;
        return Objects.equals(id, auditUnitOutDto.id) && Objects.equals(createdAt, auditUnitOutDto.createdAt)
                && Objects.equals(updatedAt, auditUnitOutDto.updatedAt) && Objects.equals(user, auditUnitOutDto.user)
                && Objects.equals(text, auditUnitOutDto.text) && type == auditUnitOutDto.type
                && Objects.equals(essenceTypeId, auditUnitOutDto.essenceTypeId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, createdAt, updatedAt, user, text, type, essenceTypeId);
    }

    @Override
    public String toString() {
        return "AuditUnitOutDto{" +
                "id=" + id +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", user=" + user +
                ", text='" + text + '\'' +
                ", type=" + type +
                ", essenceTypeId=" + essenceTypeId +
                '}';
    }

    public static class AuditUnitOutDtoBuilder {

        private UUID id;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;
        private UserAuditOutDto user;
        private String text;
        private EEssenceType type;
        private UUID essenceTypeId;

        private AuditUnitOutDtoBuilder() {
        }

        public AuditUnitOutDto build() {
            return new AuditUnitOutDto(id, createdAt, updatedAt, user, text, type, essenceTypeId);
        }

        public AuditUnitOutDtoBuilder setId(UUID id) {
            this.id = id;
            return this;
        }

        public AuditUnitOutDtoBuilder setCreatedAt(LocalDateTime createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public AuditUnitOutDtoBuilder setUpdatedAt(LocalDateTime updatedAt) {
            this.updatedAt = updatedAt;
            return this;
        }

        public AuditUnitOutDtoBuilder setUser(UserAuditOutDto user) {
            this.user = user;
            return this;
        }

        public AuditUnitOutDtoBuilder setText(String text) {
            this.text = text;
            return this;
        }

        public AuditUnitOutDtoBuilder setType(EEssenceType type) {
            this.type = type;
            return this;
        }

        public AuditUnitOutDtoBuilder setEssenceTypeId(UUID essenceTypeId) {
            this.essenceTypeId = essenceTypeId;
            return this;
        }
    }
}
