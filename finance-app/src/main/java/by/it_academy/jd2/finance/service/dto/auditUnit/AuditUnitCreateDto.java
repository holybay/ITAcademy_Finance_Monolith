package by.it_academy.jd2.finance.service.dto.auditUnit;


import by.it_academy.jd2.finance.repository.entity.auditUnit.EEssenceType;

import java.util.Objects;
import java.util.UUID;

public class AuditUnitCreateDto {

    private UUID userId;
    private String text;
    private EEssenceType type;
    private UUID essenceTypeId;

    private AuditUnitCreateDto() {
    }

    private AuditUnitCreateDto(UUID userId, String text, EEssenceType type, UUID essenceTypeId) {
        this.userId = userId;
        this.text = text;
        this.type = type;
        this.essenceTypeId = essenceTypeId;
    }

    public static AuditUnitCreateDtoBuilder builder() {
        return new AuditUnitCreateDtoBuilder();
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
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
        AuditUnitCreateDto that = (AuditUnitCreateDto) o;
        return Objects.equals(userId, that.userId) && Objects.equals(text, that.text)
                && type == that.type && Objects.equals(essenceTypeId, that.essenceTypeId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, text, type, essenceTypeId);
    }

    @Override
    public String toString() {
        return "AuditUnitCreateDto{" +
                "userId=" + userId +
                ", text='" + text + '\'' +
                ", type=" + type +
                ", essenceTypeId=" + essenceTypeId +
                '}';
    }

    public static class AuditUnitCreateDtoBuilder {

        private UUID userId;
        private String text;
        private EEssenceType type;
        private UUID essenceTypeId;

        private AuditUnitCreateDtoBuilder() {
        }

        public AuditUnitCreateDto build() {
            return new AuditUnitCreateDto(userId, text, type, essenceTypeId);
        }

        public AuditUnitCreateDtoBuilder setUserId(UUID userId) {
            this.userId = userId;
            return this;
        }

        public AuditUnitCreateDtoBuilder setText(String text) {
            this.text = text;
            return this;
        }

        public AuditUnitCreateDtoBuilder setType(EEssenceType type) {
            this.type = type;
            return this;
        }

        public AuditUnitCreateDtoBuilder setEssenceTypeId(UUID essenceTypeId) {
            this.essenceTypeId = essenceTypeId;
            return this;
        }
    }
}
