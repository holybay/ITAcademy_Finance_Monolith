package by.it_academy.jd2.finance.repository;

import by.it_academy.jd2.finance.repository.entity.account.Account;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface IAccountRepository extends JpaRepository<Account, UUID> {

    Optional<Account> findByIdAndUserId(UUID id, UUID userId);

    Page<Account> findAllByUserId(Pageable pageable, UUID userId);

    long countByUserId(UUID userId);
}
