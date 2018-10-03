package ua.nure.semianikhin.elective.exception;

public class TagAlreadyExistException extends Exception {

    private static final long serialVersionUID = -216576639569588533L;

    public TagAlreadyExistException(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }

    @Override
    public synchronized Throwable getCause() {
        return super.getCause();
    }
}
