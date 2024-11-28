package by.it_academy.jd2.finance.platform.exception;

public class AppAuthzException extends ApplicationException {

    public AppAuthzException() {
        super();
    }

    public AppAuthzException(Throwable cause) {
        super(cause);
    }

    public AppAuthzException(String message) {
        super(message);
    }

    public AppAuthzException(String message, Throwable cause) {
        super(message, cause);
    }
}
