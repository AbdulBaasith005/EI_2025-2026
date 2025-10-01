package office.command;

import java.util.*;
import office.config.OfficeConfig;
import office.config.Room;
import office.exception.InvalidConfigurationException;

public class RoomStatusCommand implements Command {
    @Override
    public void execute() {
        Collection<Room> rooms = OfficeConfig.getInstance().getAllRooms();

        if (rooms.isEmpty()) {
            throw new InvalidConfigurationException("Rooms are not configured. Please configure rooms first.");
        }

        System.out.println("=== Room Status ===");
        for (Room room : rooms) {
            System.out.println("Room " + room.getId() +
                    " | Booked: " + room.isBooked() +
                    " | Occupants: " + room.getOccupants() +
                    " | Booking start: " + room.getBookingStart() +
                    " | Duration: " + room.getBookingDurationMinutes() + " mins");
        }
    }
}
