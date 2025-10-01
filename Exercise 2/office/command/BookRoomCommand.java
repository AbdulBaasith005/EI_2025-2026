package office.command;

import java.time.LocalDateTime;
import java.util.Optional;
import office.config.OfficeConfig;
import office.config.Room;
import office.exception.InvalidTimeException;
import office.exception.RoomAlreadyBookedException;
import office.exception.RoomNotFoundException;

public class BookRoomCommand implements Command {
    private final int roomId;
    private final LocalDateTime start;
    private final int duration;

    public BookRoomCommand(int roomId, LocalDateTime start, int duration) {
        this.roomId = roomId;
        this.start = start;
        this.duration = duration;
    }

    @Override
    public void execute() {
        if (start.isBefore(LocalDateTime.now())) {
            throw new InvalidTimeException("Booking time cannot be in the past: " + start);
        }

        Optional<Room> opt = OfficeConfig.getInstance().getRoom(roomId);
        Room room = opt.orElseThrow(() -> {
            return new RoomNotFoundException(roomId);
        });

        if (room.isBooked()) {
            throw new RoomAlreadyBookedException(roomId);
        }

        room.book(start, duration);
    }
}
