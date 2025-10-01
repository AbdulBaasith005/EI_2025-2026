package office.exception;

public class InvalidTimeException extends RuntimeException {
    public InvalidTimeException(String time) {
        super("Invalid time provided: " + time);
    }
}
