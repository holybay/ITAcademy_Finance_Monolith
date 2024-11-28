package by.it_academy.jd2.finance.service.validation.impl;

import by.it_academy.jd2.finance.repository.IUserRepository;
import by.it_academy.jd2.finance.repository.entity.user.EUserStatus;
import by.it_academy.jd2.finance.repository.entity.user.User;
import by.it_academy.jd2.finance.service.validation.IUserValidator;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
public class UserValidator implements IUserValidator {

    private final IUserRepository userRepository;

    public UserValidator(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public boolean validate(UUID id) {
        Optional<User> user = userRepository.findById(id);
        return user.isPresent() && user.get().getStatus() != EUserStatus.DEACTIVATED;
    }
}
