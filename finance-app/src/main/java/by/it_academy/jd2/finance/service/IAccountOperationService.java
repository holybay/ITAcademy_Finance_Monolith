package by.it_academy.jd2.finance.service;


import java.math.BigDecimal;
import java.util.UUID;

public interface IAccountOperationService {

    BigDecimal getAccountBalance(UUID id);
}
