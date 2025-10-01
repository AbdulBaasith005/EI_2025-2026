package office.exception;

public class CapacityExceededException extends RuntimeException {
    public CapacityExceededException(int roomId, int occupants, int capacity) {
        super("Room " + roomId + " capacity exceeded: " + occupants + " occupants (max " + capacity + ").");
    }
}
