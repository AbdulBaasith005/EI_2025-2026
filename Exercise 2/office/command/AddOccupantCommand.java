package office.command;

import java.util.Optional;
import office.config.OfficeConfig;
import office.config.Room;
import office.exception.CapacityExceededException;
import office.exception.RoomNotBookedException;
import office.exception.RoomNotFoundException;

public class AddOccupantCommand implements Command {

    private final int roomId;
    private final int occupants;

    public AddOccupantCommand(int roomId, int occupants) {
        this.roomId = roomId;
        this.occupants = occupants;
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

        if (occupants > room.getCapacity()) {
            throw new CapacityExceededException(roomId, occupants, room.getCapacity());
        }

        room.setOccupants(occupants);
    }
}
