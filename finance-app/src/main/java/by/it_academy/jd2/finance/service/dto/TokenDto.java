package by.it_academy.jd2.finance.service.dto;

import java.util.Objects;
import java.util.UUID;

public class TokenDto {

    private UUID userId;
    private String role;

    private TokenDto(UUID userId, String role) {
        this.userId = userId;
        this.role = role;
    }

    public static TokenDtoBuilder builder() {
        return new TokenDtoBuilder();
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TokenDto tokenDto = (TokenDto) o;
        return Objects.equals(userId, tokenDto.userId) && Objects.equals(role, tokenDto.role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, role);
    }

    @Override
    public String toString() {
        return "TokenDto{" +
                "userId=" + userId +
                ", role='" + role + '\'' +
                '}';
    }

    public static class TokenDtoBuilder {

        private UUID userId;
        private String role;

        private TokenDtoBuilder() {
        }

        public TokenDto build() {
            return new TokenDto(userId, role);
        }

        public TokenDtoBuilder setUserId(UUID userId) {
            this.userId = userId;
            return this;
        }

        public TokenDtoBuilder setRole(String role) {
            this.role = role;
            return this;
        }
    }
}
