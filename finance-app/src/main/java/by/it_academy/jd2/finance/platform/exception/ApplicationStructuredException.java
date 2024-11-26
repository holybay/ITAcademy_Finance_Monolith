package by.it_academy.jd2.finance.platform.exception;

public class ApplicationStructuredException extends ApplicationException {

    private final String field;

    public ApplicationStructuredException(String field, String message) {
        super(message);
        this.field = field;
    }

    public ApplicationStructuredException(String field, String message, Throwable cause) {
        super(message, cause);
        this.field = field;
    }

    public String getField() {
        return field;
    }
}
