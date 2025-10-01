package office.config;

import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import office.exception.*;
import office.log.LoggerFactory;
import office.observer.OccupancyObserver;
import office.observer.OccupancySubject;

public class Room implements OccupancySubject {

    private final int id;
    private int capacity = 10;
    private boolean booked = false;
    private boolean occupied = false;
    private int currentOccupants = 0;
    private final List<OccupancyObserver> observers = new ArrayList<>();
    private LocalDateTime bookingStart;
    private int bookingDurationMinutes;
    private final ScheduledExecutorService scheduler;
    private ScheduledFuture<?> autoReleaseFuture;

    private static final Logger logger = LoggerFactory.getLogger();

    public Room(int id, ScheduledExecutorService scheduler) {
        this.id = id;
        this.scheduler = scheduler;
    }

    public int getId() { return id; }

    public int getCapacity() { return capacity; }

    public boolean isBooked() { return booked; }

    public boolean isOccupied() { return occupied; }

    public int getOccupants() { return currentOccupants; }

    public LocalDateTime getBookingStart() { return bookingStart; }

    public int getBookingDurationMinutes() { return bookingDurationMinutes; }

    public void setCapacity(int capacity) {
        if (capacity <= 0) {
            logger.log(Level.SEVERE, () -> "Invalid capacity " + capacity + " for Room " + id);
            throw new InvalidConfigurationException("Room " + id + " capacity must be positive.");
        }
        this.capacity = capacity;
        logger.log(Level.INFO, () -> "Room " + id + " capacity set to " + capacity);
    }

    public void book(LocalDateTime start, int durationMinutes) {
        if (booked) {
            logger.log(Level.WARNING, () -> "Attempted to book already booked Room " + id);
            throw new RoomAlreadyBookedException(id);
        }
        booked = true;
        bookingStart = start;
        bookingDurationMinutes = durationMinutes;
        logger.log(Level.INFO, () -> "Room " + id + " booked from " + start + " for " + durationMinutes + " minutes");
        scheduleAutoReleaseIfUnoccupied();
    }

    public void cancelBooking() {
        if (!booked) {
            logger.log(Level.WARNING, () -> "Attempted to cancel unbooked Room " + id);
            throw new RoomNotBookedException(id);
        }
        if (currentOccupants > 0) {
            logger.log(Level.WARNING, () -> "Attempted to cancel Room " + id + " which is currently occupied");
            throw new RoomOccupiedException(id);
        }

        booked = false;
        bookingStart = null;
        bookingDurationMinutes = 0;
        logger.log(Level.INFO, () -> "Booking for Room " + id + " cancelled successfully");
        if (autoReleaseFuture != null) autoReleaseFuture.cancel(true);
    }

    public void setOccupants(int count) {
        if (count < 0) {
            logger.log(Level.SEVERE, () -> "Negative occupant count " + count + " for Room " + id);
            throw new InvalidConfigurationException("Occupants cannot be negative.");
        }

        if (count > capacity) {
            logger.log(Level.WARNING, () -> "Occupants " + count + " exceed capacity " + capacity + " for Room " + id);
            throw new CapacityExceededException(id, count, capacity);
        }

        currentOccupants = count;

        if (count == 0) {
            occupied = false;
            logger.log(Level.INFO, () -> "Room " + id + " is now unoccupied");
            notifyObservers(false);
        } else if (count < 2) {
            logger.log(Level.INFO, () -> "Room " + id + " occupancy insufficient to mark as occupied: " + count);
        } else {
            occupied = true;
            logger.log(Level.INFO, () -> "Room " + id + " is now occupied by " + count + " persons");
            notifyObservers(true);
        }
    }

    private void scheduleAutoReleaseIfUnoccupied() {
        autoReleaseFuture = scheduler.schedule(() -> {
            synchronized (Room.this) {
                if (!isOccupied()) {
                    booked = false;
                    bookingStart = null;
                    bookingDurationMinutes = 0;
                    logger.log(Level.INFO, () -> "Room " + id + " auto-released. AC and lights off.");
                    notifyObservers(false);
                }
            }
        }, 5, TimeUnit.MINUTES);
    }

    @Override
    public void addObserver(OccupancyObserver observer) { observers.add(observer); }

    @Override
    public void removeObserver(OccupancyObserver observer) { observers.remove(observer); }

    @Override
    public void notifyObservers(boolean occupied) {
        for (OccupancyObserver obs : observers) obs.update(id, occupied);
    }
}
