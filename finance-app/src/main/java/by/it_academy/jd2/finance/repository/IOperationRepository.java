package by.it_academy.jd2.finance.repository;

import by.it_academy.jd2.finance.repository.entity.Operation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface IOperationRepository extends JpaRepository<Operation, UUID> {

    Page<Operation> findAllByAccountId(Pageable pageable, UUID accountId);

    long countByAccountId(UUID accountId);
}
