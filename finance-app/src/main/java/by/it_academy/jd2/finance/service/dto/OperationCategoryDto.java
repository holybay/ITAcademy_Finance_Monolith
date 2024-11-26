package by.it_academy.jd2.finance.service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

public class OperationCategoryDto {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private UUID id;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDateTime createdAt;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDateTime updatedAt;
    @NotBlank(message = "Title field can't be blank!")
    private String title;

    private OperationCategoryDto(UUID id, LocalDateTime createdAt, LocalDateTime updatedAt, String title) {
        this.id = id;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.title = title;
    }

    public static OperationCategoryDtoBuilder builder() {
        return new OperationCategoryDtoBuilder();
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OperationCategoryDto that = (OperationCategoryDto) o;
        return Objects.equals(id, that.id) && Objects.equals(createdAt, that.createdAt)
                && Objects.equals(updatedAt, that.updatedAt) && Objects.equals(title, that.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, createdAt, updatedAt, title);
    }

    @Override
    public String toString() {
        return "OperationCategoryDto{" +
                "id=" + id +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", title='" + title + '\'' +
                '}';
    }

    public static class OperationCategoryDtoBuilder {

        private UUID id;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;
        private String title;

        private OperationCategoryDtoBuilder() {
        }

        public OperationCategoryDto build() {
            return new OperationCategoryDto(id, createdAt, updatedAt, title);
        }

        public OperationCategoryDtoBuilder setId(UUID id) {
            this.id = id;
            return this;
        }

        public OperationCategoryDtoBuilder setCreatedAt(LocalDateTime createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public OperationCategoryDtoBuilder setUpdatedAt(LocalDateTime updatedAt) {
            this.updatedAt = updatedAt;
            return this;
        }

        public OperationCategoryDtoBuilder setTitle(String title) {
            this.title = title;
            return this;
        }
    }
}
