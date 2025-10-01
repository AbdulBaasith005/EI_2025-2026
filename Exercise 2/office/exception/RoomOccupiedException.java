package office.exception;

public class RoomOccupiedException extends RuntimeException {
    public RoomOccupiedException(int roomId) {
        super("Room " + roomId + " cannot be cancelled. Occupants still present.");
    }
}
