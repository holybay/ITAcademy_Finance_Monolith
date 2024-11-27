package by.it_academy.jd2.finance.repository;

import by.it_academy.jd2.finance.repository.entity.AccountOperation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

public interface IAccountOperationRepository extends JpaRepository<AccountOperation, UUID> {

    @Query("SELECT SUM(o.value) from AccountOperation o WHERE o.account.id = :id")
    Optional<BigDecimal> sumByAccountId(@Param("id") UUID id);
}
