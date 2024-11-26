package by.it_academy.jd2.finance.service.dto;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

public class UpdateCoordinate {

    private UUID id;
    private LocalDateTime updatedAt;

    private UpdateCoordinate(UUID id, LocalDateTime updatedAt) {
        this.id = id;
        this.updatedAt = updatedAt;
    }

    public static UpdateCoordinateBuilder builder() {
        return new UpdateCoordinateBuilder();
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UpdateCoordinate that = (UpdateCoordinate) o;
        return Objects.equals(id, that.id) && Objects.equals(updatedAt, that.updatedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, updatedAt);
    }

    @Override
    public String toString() {
        return "UpdateCoordinate{" +
                "id=" + id +
                ", updatedAt=" + updatedAt +
                '}';
    }

    public static class UpdateCoordinateBuilder {

        private UUID id;
        private LocalDateTime updatedAt;

        private UpdateCoordinateBuilder() {
        }

        public UpdateCoordinateBuilder setId(UUID id) {
            this.id = id;
            return this;
        }

        public UpdateCoordinateBuilder setUpdatedAt(LocalDateTime updatedAt) {
            this.updatedAt = updatedAt;
            return this;
        }

        public UpdateCoordinate build() {
            return new UpdateCoordinate(id, updatedAt);
        }
    }
}
