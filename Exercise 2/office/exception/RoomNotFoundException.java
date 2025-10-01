package office.exception;

public class RoomNotFoundException extends RuntimeException {
    public RoomNotFoundException(int roomId) {
        super("Room " + roomId + " does not exist.");
    }
}
