package by.it_academy.jd2.finance.repository.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.persistence.Version;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "operation_categories")
public class Category {

    @Id
    @Column(name = "id", updatable = false)
    private UUID id;

    @Column(name = "created_at", updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    @Version
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime updatedAt;

    @Column(name = "title")
    private String title;

    public Category() {
    }

    private Category(UUID id, LocalDateTime createdAt, LocalDateTime updatedAt, String title) {
        this.id = id;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.title = title;
    }

    public static CategoryBuilder builder() {
        return new CategoryBuilder();
    }

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = this.createdAt;
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
        Category that = (Category) o;
        return Objects.equals(id, that.id) && Objects.equals(createdAt, that.createdAt)
                && Objects.equals(updatedAt, that.updatedAt) && Objects.equals(title, that.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, createdAt, updatedAt, title);
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", title='" + title + '\'' +
                '}';
    }

    public static class CategoryBuilder {

        private UUID id;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;
        private String title;

        private CategoryBuilder() {
        }

        public Category build() {
            return new Category(id, createdAt, updatedAt, title);
        }

        public CategoryBuilder setId(UUID id) {
            this.id = id;
            return this;
        }

        public CategoryBuilder setCreatedAt(LocalDateTime createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public CategoryBuilder setUpdatedAt(LocalDateTime updatedAt) {
            this.updatedAt = updatedAt;
            return this;
        }

        public CategoryBuilder setTitle(String title) {
            this.title = title;
            return this;
        }
    }
}
