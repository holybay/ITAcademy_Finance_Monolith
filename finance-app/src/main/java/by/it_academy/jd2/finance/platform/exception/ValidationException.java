package by.it_academy.jd2.finance.platform.exception;

import java.util.ArrayList;
import java.util.List;

public class ValidationException extends ApplicationException {

    private final List<ValidationStructuredException> errors;

    public ValidationException() {
        errors = new ArrayList<>();
    }

    public ValidationException(ValidationStructuredException e) {
        this();
        errors.add(e);
    }

    public ValidationException(String field, String message) {
        this();
        errors.add(new ValidationStructuredException(field, message));
    }

    public ValidationException(List<ValidationStructuredException> errors) {
        this.errors = errors;
    }

    public List<ValidationStructuredException> getErrors() {
        return errors;
    }

    public void setErrors(List<ApplicationStructuredException> errors) {
    }
}
