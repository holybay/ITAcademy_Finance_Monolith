package by.it_academy.jd2.finance.repository.entity.user;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.persistence.Version;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "users")
public class User {

    @Id
    @Column(name = "id", updatable = false)
    private UUID id;

    @Column(name = "created_at", updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime createdAt;

    @Version
    @Column(name = "updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime updatedAt;

    @Column(name = "mail")
    private String mail;

    @Column(name = "full_name")
    private String fio;

    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private EUserRole role;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private EUserStatus status;

    @Column(name = "password")
    private String password;

    public User() {
    }

    private User(UUID id, LocalDateTime createdAt, LocalDateTime updatedAt, String mail,
                 String fio, EUserRole role, EUserStatus status, String password) {
        this.id = id;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.mail = mail;
        this.fio = fio;
        this.role = role;
        this.status = status;
        this.password = password;
    }

    public static UserBuilder builder() {
        return new UserBuilder();
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

    public EUserStatus getStatus() {
        return status;
    }

    public void setStatus(EUserStatus status) {
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
        User user = (User) o;
        return Objects.equals(id, user.id) && Objects.equals(createdAt, user.createdAt) && Objects.equals(updatedAt, user.updatedAt) && Objects.equals(mail, user.mail) && Objects.equals(password, user.password) && Objects.equals(fio, user.fio) && role == user.role && status == user.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, createdAt, updatedAt, mail, password, fio, role, status);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", mail='" + mail + '\'' +
                ", password='" + password + '\'' +
                ", fio='" + fio + '\'' +
                ", role=" + role +
                ", status=" + status +
                '}';
    }

    public static class UserBuilder {

        private UUID id;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;
        private String mail;
        private String fio;
        private EUserRole role;
        private EUserStatus status;
        private String password;

        private UserBuilder() {
        }

        public User build() {
            return new User(id, createdAt, updatedAt, mail, fio, role, status, password);
        }

        public UserBuilder setId(UUID id) {
            this.id = id;
            return this;
        }

        public UserBuilder setCreatedAt(LocalDateTime createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public UserBuilder setUpdatedAt(LocalDateTime updatedAt) {
            this.updatedAt = updatedAt;
            return this;
        }

        public UserBuilder setMail(String mail) {
            this.mail = mail;
            return this;
        }

        public UserBuilder setFio(String fio) {
            this.fio = fio;
            return this;
        }

        public UserBuilder setRole(EUserRole role) {
            this.role = role;
            return this;
        }

        public UserBuilder setStatus(EUserStatus status) {
            this.status = status;
            return this;
        }

        public UserBuilder setPassword(String password) {
            this.password = password;
            return this;
        }
    }
}
