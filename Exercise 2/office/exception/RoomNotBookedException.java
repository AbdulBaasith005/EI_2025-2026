package office.exception;

public class RoomNotBookedException extends RuntimeException {
    public RoomNotBookedException(int roomId) {
        super("Room " + roomId + " is not booked. Cannot cancel booking or cannot add occupants.");
    }
}

