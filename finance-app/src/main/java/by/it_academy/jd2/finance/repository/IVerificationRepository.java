package by.it_academy.jd2.finance.repository;

import by.it_academy.jd2.finance.repository.entity.VerificationUnit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.UUID;

public interface IVerificationRepository extends JpaRepository<VerificationUnit, UUID> {

    @Query("SELECT vu FROM VerificationUnit vu WHERE vu.user.mail = :mail")
    Optional<VerificationUnit> findByEmail(@Param("mail") String mail);
}
