package by.it_academy.jd2.finance.service.dto.user;

import by.it_academy.jd2.finance.repository.entity.user.EUserRole;
import by.it_academy.jd2.finance.repository.entity.user.EUserStatus;
import by.it_academy.jd2.finance.service.validation.EnumValidator;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.Objects;

public class UserUpdateDto {

    @NotBlank(message = "User email can't be blank!")
    @Email(message = "Not an email address!")
    private String mail;

    @NotBlank(message = "FIO can't be blank!")
    private String fio;

    @NotBlank(message = "User role can't be blank!")
    @EnumValidator(enumClass = EUserRole.class)
    private String role;

    @NotBlank(message = "User status can't be blank!")
    @EnumValidator(enumClass = EUserStatus.class)
    private String status;

    @NotBlank(message = "Password can't be blank!")
    @Size(min = 8, message = "Min password length is 8 symbols!")
    private String password;

    private UserUpdateDto(String mail, String fio, String role,
                          String status, String password) {
        this.mail = mail;
        this.fio = fio;
        this.role = role;
        this.status = status;
        this.password = password;
    }

    public static UserUpdateDtoBuilder builder() {
        return new UserUpdateDtoBuilder();
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserUpdateDto that = (UserUpdateDto) o;
        return Objects.equals(mail, that.mail) && Objects.equals(fio, that.fio) && Objects.equals(role, that.role) && Objects.equals(status, that.status) && Objects.equals(password, that.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mail, fio, role, status, password);
    }

    @Override
    public String toString() {
        return "UserUpdateDto{" +
                "mail='" + mail + '\'' +
                ", fio='" + fio + '\'' +
                ", role='" + role + '\'' +
                ", status='" + status + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public static class UserUpdateDtoBuilder {

        private String mail;
        private String fio;
        private String role;
        private String status;
        private String password;

        private UserUpdateDtoBuilder() {
        }

        public UserUpdateDto build() {
            return new UserUpdateDto(mail, fio, role, status, password);
        }

        public UserUpdateDtoBuilder setMail(String mail) {
            this.mail = mail;
            return this;
        }

        public UserUpdateDtoBuilder setFio(String fio) {
            this.fio = fio;
            return this;
        }

        public UserUpdateDtoBuilder setRole(String role) {
            this.role = role;
            return this;
        }

        public UserUpdateDtoBuilder setStatus(String status) {
            this.status = status;
            return this;
        }

        public UserUpdateDtoBuilder setPassword(String password) {
            this.password = password;
            return this;
        }
    }
}
