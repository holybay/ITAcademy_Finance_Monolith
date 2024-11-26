package by.it_academy.jd2.finance.service.dto.user;

import by.it_academy.jd2.finance.platform.util.CustomLocalDateTimeSerializer;
import by.it_academy.jd2.finance.repository.entity.user.EUserRole;
import by.it_academy.jd2.finance.repository.entity.user.EUserStatus;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

public class UserOutDto {

    @JsonProperty("uuid")
    private UUID id;

    @JsonProperty("dt_create")
    @JsonSerialize(using = CustomLocalDateTimeSerializer.class)
    private LocalDateTime createdAt;

    @JsonProperty("dt_update")
    @JsonSerialize(using = CustomLocalDateTimeSerializer.class)
    private LocalDateTime updatedAt;

    private String mail;
    private String fio;
    private EUserRole role;
    private EUserStatus status;

    private UserOutDto(UUID id, LocalDateTime createdAt, LocalDateTime updatedAt, String mail, String fio,
                       EUserRole role, EUserStatus status) {
        this.id = id;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.mail = mail;
        this.fio = fio;
        this.role = role;
        this.status = status;
    }

    public static UserOutDtoBuilder builder() {
        return new UserOutDtoBuilder();
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

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public EUserRole getRole() {
        return role;
    }

    public void setRole(EUserRole role) {
        this.role = role;
    }

    public EUserStatus getStatus() {
        return status;
    }

    public void setStatus(EUserStatus status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserOutDto that = (UserOutDto) o;
        return Objects.equals(id, that.id) && Objects.equals(createdAt, that.createdAt) && Objects.equals(updatedAt, that.updatedAt) && Objects.equals(mail, that.mail) && Objects.equals(fio, that.fio) && role == that.role && status == that.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, createdAt, updatedAt, mail, fio, role, status);
    }

    @Override
    public String toString() {
        return "UserOutDto{" +
                "id=" + id +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", mail='" + mail + '\'' +
                ", fio='" + fio + '\'' +
                ", role=" + role +
                ", status=" + status +
                '}';
    }

    public static class UserOutDtoBuilder {

        private UUID id;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;
        private String mail;
        private String fio;
        private EUserRole role;
        private EUserStatus status;

        private UserOutDtoBuilder() {
        }

        public UserOutDto build() {
            return new UserOutDto(id, createdAt, updatedAt, mail, fio, role, status);
        }

        public UserOutDtoBuilder setId(UUID id) {
            this.id = id;
            return this;
        }

        public UserOutDtoBuilder setCreatedAt(LocalDateTime createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public UserOutDtoBuilder setUpdatedAt(LocalDateTime updatedAt) {
            this.updatedAt = updatedAt;
            return this;
        }

        public UserOutDtoBuilder setMail(String mail) {
            this.mail = mail;
            return this;
        }

        public UserOutDtoBuilder setFio(String fio) {
            this.fio = fio;
            return this;
        }

        public UserOutDtoBuilder setRole(EUserRole role) {
            this.role = role;
            return this;
        }

        public UserOutDtoBuilder setStatus(EUserStatus status) {
            this.status = status;
            return this;
        }
    }
}
