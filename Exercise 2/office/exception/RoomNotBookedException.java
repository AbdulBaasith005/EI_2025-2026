package office.exception;

public class RoomNotBookedException extends RuntimeException {
    public RoomNotBookedException(int roomId) {
        super("Room " + roomId + " is not booked. Occupants cannot be added.");
    }
}
