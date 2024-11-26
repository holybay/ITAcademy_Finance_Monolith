package by.it_academy.jd2.finance.repository;

import by.it_academy.jd2.finance.repository.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface IUserRepository extends JpaRepository<User, UUID> {

    Optional<User> findByMail(String mail);
}
