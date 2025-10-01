package office.config;

import java.util.*;
import java.util.concurrent.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import office.exception.RoomNotFoundException;
import office.log.LoggerFactory;

public class OfficeConfig {

    private static OfficeConfig instance;
    private final Map<Integer, Room> rooms = new HashMap<>();
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(2);
    private static final Logger logger = LoggerFactory.getLogger();

    private OfficeConfig() {
        logger.log(Level.INFO, "OfficeConfig instance created.");
    }

    public static synchronized OfficeConfig getInstance() {
        if (instance == null) {
            instance = new OfficeConfig();
            logger.log(Level.INFO, "OfficeConfig singleton initialized.");
        }
        return instance;
    }

    public boolean areRoomsConfigured() {
        return !rooms.isEmpty();
    }

    public void configureRooms(int count) {
        rooms.clear();
        for (int i = 1; i <= count; i++) {
            Room room = new Room(i, scheduler);
            room.addObserver(new LightSystem());
            room.addObserver(new AirConditioningSystem());
            rooms.put(i, room);
        }
        logger.log(Level.INFO, "Office configured with {0} meeting rooms.", count);
        System.out.println("Office configured with " + count + " meeting rooms.");
    }

    public Optional<Room> getRoom(int id) {
        Optional<Room> roomOpt = Optional.ofNullable(rooms.get(id));
        if (roomOpt.isEmpty()) {
            throw new RoomNotFoundException(id);
        }
        return roomOpt;
    }

    public Collection<Room> getAllRooms() {
        return rooms.values();
    }

    public void shutdown() {
        scheduler.shutdownNow();
        logger.log(Level.INFO, "Scheduler shut down.");
        System.out.println("Scheduler shut down. Resources released.");
    }
}
