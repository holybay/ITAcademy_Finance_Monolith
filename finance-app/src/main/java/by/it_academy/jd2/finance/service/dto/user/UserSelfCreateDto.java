package by.it_academy.jd2.finance.service.dto.user;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class UserSelfCreateDto extends CreateDtoCommon {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String role;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String status;

    public UserSelfCreateDto(String role, String status) {
        this.role = role;
        this.status = status;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        UserSelfCreateDto that = (UserSelfCreateDto) o;
        return Objects.equals(role, that.role) && Objects.equals(status, that.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), role, status);
    }

    @Override
    public String toString() {
        return "UserSelfCreateDto{" +
                "role='" + role + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
