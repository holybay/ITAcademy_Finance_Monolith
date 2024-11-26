package by.it_academy.jd2.finance.service.dto.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import java.util.Objects;

public class UserVerificationDto {

    @JsonProperty
    private String code;
    @NotBlank(message = "Email can't be blank!")
    @Email(message = "Provide your email!")
    private String mail;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserVerificationDto that = (UserVerificationDto) o;
        return Objects.equals(code, that.code) && Objects.equals(mail, that.mail);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code, mail);
    }

    @Override
    public String toString() {
        return "UserVerificationCoordinate{" +
                "code='" + code + '\'' +
                ", mail='" + mail + '\'' +
                '}';
    }
}
