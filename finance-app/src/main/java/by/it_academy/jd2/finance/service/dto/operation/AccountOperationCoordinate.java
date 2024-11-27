package by.it_academy.jd2.finance.service.dto.operation;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

public class AccountOperationCoordinate {

    private UUID accountId;
    private LocalDateTime updatedAt;
    private UUID operationId;

    private AccountOperationCoordinate(UUID accountId, LocalDateTime updatedAt, UUID operationId) {
        this.accountId = accountId;
        this.updatedAt = updatedAt;
        this.operationId = operationId;
    }

    public static AccountOperationCoordinateBuilder builder() {
        return new AccountOperationCoordinateBuilder();
    }

    public UUID getAccountId() {
        return accountId;
    }

    public void setAccountId(UUID accountId) {
        this.accountId = accountId;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public UUID getOperationId() {
        return operationId;
    }

    public void setOperationId(UUID operationId) {
        this.operationId = operationId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AccountOperationCoordinate that = (AccountOperationCoordinate) o;
        return Objects.equals(accountId, that.accountId) && Objects.equals(updatedAt, that.updatedAt) && Objects.equals(operationId, that.operationId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountId, updatedAt, operationId);
    }

    @Override
    public String toString() {
        return "AccountOperationCoordinate{" +
                "accountId=" + accountId +
                ", updatedAt=" + updatedAt +
                ", operationId=" + operationId +
                '}';
    }

    public static class AccountOperationCoordinateBuilder {

        private UUID accountId;
        private LocalDateTime updatedAt;
        private UUID operationId;

        private AccountOperationCoordinateBuilder() {
        }

        public AccountOperationCoordinateBuilder setAccountId(UUID accountId) {
            this.accountId = accountId;
            return this;
        }

        public AccountOperationCoordinateBuilder setUpdatedAt(LocalDateTime updatedAt) {
            this.updatedAt = updatedAt;
            return this;
        }

        public AccountOperationCoordinateBuilder setOperationId(UUID operationId) {
            this.operationId = operationId;
            return this;
        }

        public AccountOperationCoordinate build() {
            return new AccountOperationCoordinate(accountId, updatedAt, operationId);
        }
    }

}
