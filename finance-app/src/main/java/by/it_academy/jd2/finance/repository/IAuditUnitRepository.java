package by.it_academy.jd2.finance.repository;


import by.it_academy.jd2.finance.repository.entity.auditUnit.AuditUnit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface IAuditUnitRepository extends JpaRepository<AuditUnit, UUID> {
}
