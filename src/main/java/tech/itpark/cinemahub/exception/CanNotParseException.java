package tech.itpark.cinemahub.exception;

public class CanNotParseException extends RuntimeException {

    public CanNotParseException() {
        super();
    }

    public CanNotParseException(String message) {
        super(message);
    }

    public CanNotParseException(String message, Throwable cause) {
        super(message, cause);
    }

    public CanNotParseException(Throwable cause) {
        super(cause);
    }

    protected CanNotParseException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
