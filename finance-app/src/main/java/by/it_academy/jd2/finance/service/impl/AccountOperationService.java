package by.it_academy.jd2.finance.service.impl;

import by.it_academy.jd2.finance.repository.IAccountOperationRepository;
import by.it_academy.jd2.finance.service.IAccountOperationService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.UUID;

@Service
public class AccountOperationService implements IAccountOperationService {

    private final IAccountOperationRepository operationRepository;

    public AccountOperationService(IAccountOperationRepository operationRepository) {
        this.operationRepository = operationRepository;
    }

    @Override
    public BigDecimal getAccountBalance(UUID id) {
        return operationRepository.sumByAccountId(id).orElse(BigDecimal.ZERO);
    }
}
