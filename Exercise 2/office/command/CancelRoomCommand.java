package office.command;

import java.util.Optional;
import office.config.OfficeConfig;
import office.config.Room;
import office.exception.RoomNotBookedException;
import office.exception.RoomNotFoundException;
import office.exception.RoomOccupiedException;

public class CancelRoomCommand implements Command {
    private final int roomId;

    public CancelRoomCommand(int roomId) {
        this.roomId = roomId;
    }

    @Override
    public void execute() {
        Optional<Room> opt = OfficeConfig.getInstance().getRoom(roomId);
        Room room = opt.orElseThrow(() -> {
            return new RoomNotFoundException(roomId);
        });

        if (!room.isBooked()) {
            throw new RoomNotBookedException(roomId);
        }

        if (room.getOccupants() > 0) {
            throw new RoomOccupiedException(roomId);
        }

        room.cancelBooking();
    }
}
