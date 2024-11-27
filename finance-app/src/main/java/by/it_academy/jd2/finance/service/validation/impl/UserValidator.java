package by.it_academy.jd2.finance.service.validation.impl;

import by.it_academy.jd2.finance.platform.exception.AppAuthException;
import by.it_academy.jd2.finance.repository.IUserRepository;
import by.it_academy.jd2.finance.repository.entity.user.EUserStatus;
import by.it_academy.jd2.finance.repository.entity.user.User;
import by.it_academy.jd2.finance.service.validation.IUserValidator;
import org.springframework.stereotype.Component;

import java.util.NoSuchElementException;
import java.util.UUID;

@Component
public class UserValidator implements IUserValidator {

    private final IUserRepository userRepository;

    public UserValidator(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void validate(UUID id) {
        User user = userRepository.findById(id).orElseThrow(() ->
                new NoSuchElementException("User with such id doesn't exist!" + id));
        if (user.getStatus() == EUserStatus.DEACTIVATED) {
            throw new AppAuthException(String.format("User with %s is deactivated!", id));
        }
    }
}
