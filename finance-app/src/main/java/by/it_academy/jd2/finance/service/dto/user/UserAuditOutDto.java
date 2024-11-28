package by.it_academy.jd2.finance.service.dto.user;

import by.it_academy.jd2.finance.repository.entity.user.EUserRole;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;
import java.util.UUID;

public class UserAuditOutDto {

    @JsonProperty("uuid")
    private UUID id;
    private String mail;
    private String fio;
    private EUserRole role;

    private UserAuditOutDto(UUID id, String mail, String fio, EUserRole role) {
        this.id = id;
        this.mail = mail;
        this.fio = fio;
        this.role = role;
    }

    public static UserAuditOutDtoBuilder builder() {
        return new UserAuditOutDtoBuilder();
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserAuditOutDto that = (UserAuditOutDto) o;
        return Objects.equals(id, that.id) && Objects.equals(mail, that.mail) && Objects.equals(fio, that.fio)
                && role == that.role;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, mail, fio, role);
    }

    @Override
    public String toString() {
        return "UserAuditOutDto{" +
                "id=" + id +
                ", mail='" + mail + '\'' +
                ", fio='" + fio + '\'' +
                ", role=" + role +
                '}';
    }

    public static class UserAuditOutDtoBuilder {

        private UUID id;
        private String mail;
        private String fio;
        private EUserRole role;

        private UserAuditOutDtoBuilder() {
        }

        public UserAuditOutDto build() {
            return new UserAuditOutDto(id, mail, fio, role);
        }

        public UserAuditOutDtoBuilder setId(UUID id) {
            this.id = id;
            return this;
        }

        public UserAuditOutDtoBuilder setMail(String mail) {
            this.mail = mail;
            return this;
        }

        public UserAuditOutDtoBuilder setFio(String fio) {
            this.fio = fio;
            return this;
        }

        public UserAuditOutDtoBuilder setRole(EUserRole role) {
            this.role = role;
            return this;
        }
    }
}
