package by.it_academy.jd2.finance.repository;


import by.it_academy.jd2.finance.repository.entity.OperationCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface IOperationCategoryRepository extends JpaRepository<OperationCategory, UUID> {
}
