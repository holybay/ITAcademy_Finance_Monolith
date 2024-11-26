package by.it_academy.jd2.finance.service.validation;

import by.it_academy.jd2.finance.repository.IUserRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class UserExistValidator implements ConstraintValidator<UserExists, UUID> {

    private final IUserRepository userRepository;

    public UserExistValidator(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public boolean isValid(UUID id, ConstraintValidatorContext constraintValidatorContext) {
        return userRepository.existsById(id);
    }
}
