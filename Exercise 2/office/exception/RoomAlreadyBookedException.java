package office.exception;

public class RoomAlreadyBookedException extends RuntimeException {
    public RoomAlreadyBookedException(int roomId) {
        super("Room " + roomId + " is already booked.");
    }
}
