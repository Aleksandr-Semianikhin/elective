package ua.nure.semianikhin.elective.exception;

public class UserAlreadyExistException extends Exception {

    private static final long serialVersionUID = 9205923500997648617L;

    public UserAlreadyExistException(String message, Throwable cause) {
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
