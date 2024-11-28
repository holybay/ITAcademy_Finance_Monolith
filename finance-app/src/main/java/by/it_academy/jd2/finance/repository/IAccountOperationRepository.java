package by.it_academy.jd2.finance.repository;

import by.it_academy.jd2.finance.repository.entity.AccountOperation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface IAccountOperationRepository extends JpaRepository<AccountOperation, UUID> {

    Page<AccountOperation> findAllByAccountId(Pageable pageable, UUID accountId);

    long countByAccountId(UUID accountId);
}
