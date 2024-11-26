package by.it_academy.jd2.finance.repository;


import by.it_academy.jd2.finance.repository.entity.Currency;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ICurrencyRepository extends JpaRepository<Currency, UUID> {
}
