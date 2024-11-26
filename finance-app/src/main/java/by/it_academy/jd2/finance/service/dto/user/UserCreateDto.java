package by.it_academy.jd2.finance.service.dto.user;

import by.it_academy.jd2.finance.repository.entity.user.EUserRole;
import by.it_academy.jd2.finance.repository.entity.user.EUserStatus;
import by.it_academy.jd2.finance.service.validation.EnumValidator;

import java.util.Objects;

public class UserCreateDto extends CreateDtoCommon {

    @EnumValidator(enumClass = EUserRole.class)
    private String role;
    @EnumValidator(enumClass = EUserStatus.class)
    private String status;


    public UserCreateDto(String role, String status) {
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
        UserCreateDto that = (UserCreateDto) o;
        return Objects.equals(role, that.role) && Objects.equals(status, that.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), role, status);
    }

    @Override
    public String toString() {
        return "UserAdminCreateDto{" +
                "role='" + role + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
