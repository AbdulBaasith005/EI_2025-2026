package office.exception;

public class InvalidCommandException extends RuntimeException {
    public InvalidCommandException(String command) {
        super(command + " Type 'help' to list all valid commands.");
    }
}
