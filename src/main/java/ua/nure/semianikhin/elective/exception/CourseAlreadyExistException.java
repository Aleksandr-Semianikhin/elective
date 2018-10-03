package ua.nure.semianikhin.elective.exception;

public class CourseAlreadyExistException extends Exception {

    private static final long serialVersionUID = -7565116777572328L;

    public CourseAlreadyExistException(String message, Throwable cause) {
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
