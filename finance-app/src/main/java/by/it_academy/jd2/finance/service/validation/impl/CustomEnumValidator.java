package by.it_academy.jd2.finance.service.validation.impl;

import by.it_academy.jd2.finance.service.validation.EnumValidator;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CustomEnumValidator implements ConstraintValidator<EnumValidator, String> {

    private Set<String> values;

    @Override
    public void initialize(EnumValidator constraintAnnotation) {
        values = Stream.of(constraintAnnotation.enumClass().getEnumConstants())
                       .map(Enum::name)
                       .collect(Collectors.toSet());
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        String defaultErrorMessage = constraintValidatorContext.getDefaultConstraintMessageTemplate();
        String formattedErrorMessage = String.format(defaultErrorMessage, value, values);
        constraintValidatorContext.disableDefaultConstraintViolation();
        constraintValidatorContext.buildConstraintViolationWithTemplate(formattedErrorMessage).addConstraintViolation();
        return values.contains(value);
    }
}
