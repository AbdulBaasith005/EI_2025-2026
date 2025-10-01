package office.command;

import java.time.LocalDateTime;
import java.util.logging.Level;
import java.util.logging.Logger;
import office.config.OfficeConfig;
import office.exception.*;
import office.log.LoggerFactory;

public class CommandParser {

    private static final Logger logger = LoggerFactory.getLogger();

    public static Command parse(String line) {
        logger.log(Level.INFO, "Executing command: {0}", line);

        String[] tokens = line.trim().split("\\s+");
        if (tokens.length == 0 || tokens[0].isEmpty()) {
            throw new InvalidCommandException("Empty command");
        }

        OfficeConfig config = OfficeConfig.getInstance();

        try {
            switch (tokens[0].toLowerCase()) {

                case "config" -> {
                    if (tokens.length < 4) {
                        throw new InvalidCommandException("Incomplete config command: \"" + line + "\"");
                    }

                    if (tokens[1].equalsIgnoreCase("room") && tokens[2].equalsIgnoreCase("count")) {
                        if (tokens.length != 4) {
                            throw new InvalidCommandException("Incorrect number of arguments for config room count: \"" + line + "\"");
                        }

                        int count = Integer.parseInt(tokens[3]);
                        if (count <= 0) {
                            throw new InvalidConfigurationException("Room count must be positive.");
                        }

                        return () -> config.configureRooms(count);
                    }

                    if (tokens[1].equalsIgnoreCase("room")
                            && tokens[2].equalsIgnoreCase("max")
                            && tokens[3].equalsIgnoreCase("capacity")) {

                        if (tokens.length != 6) {
                            throw new InvalidCommandException("Incorrect number of arguments for config room max capacity: \"" + line + "\"");
                        }

                        if (!config.areRoomsConfigured()) {
                            throw new InvalidConfigurationException("Rooms are not configured. Please configure rooms first.");
                        }

                        int roomId = Integer.parseInt(tokens[4]);
                        int capacity = Integer.parseInt(tokens[5]);

                        if (roomId <= 0) {
                            throw new InvalidConfigurationException("Room ID must be positive.");
                        }
                        if (capacity <= 0) {
                            throw new InvalidConfigurationException("Room capacity must be positive.");
                        }

                        return () -> config.getRoom(roomId)
                                .ifPresent(r -> r.setCapacity(capacity));
                    }

                    throw new InvalidCommandException("Unknown config command: \"" + line + "\"");
                }

                case "book" -> {
                    if (!config.areRoomsConfigured()) {
                        throw new InvalidConfigurationException("Rooms are not configured. Please configure rooms first.");
                    }

                    if (tokens.length != 5 || !tokens[1].equalsIgnoreCase("room")) {
                        throw new InvalidCommandException("Unknown or incomplete book command: \"" + line + "\"");
                    }

                    int roomId = Integer.parseInt(tokens[2]);
                    int duration = Integer.parseInt(tokens[4]);
                    String[] hm = tokens[3].split(":");
                    int hour = Integer.parseInt(hm[0]);
                    int minute = Integer.parseInt(hm[1]);

                    if (roomId <= 0) {
                        throw new InvalidConfigurationException("Room ID must be positive.");
                    }

                    if (hour < 0 || hour > 23 || minute < 0 || minute > 59 || duration <= 0) {
                        throw new InvalidTimeException(hour + ":" + minute + " with duration " + duration + "mins is not a valid time.");
                    }

                    LocalDateTime start = LocalDateTime.now()
                            .withHour(hour)
                            .withMinute(minute)
                            .withSecond(0)
                            .withNano(0);

                    return new BookRoomCommand(roomId, start, duration);
                }

                case "cancel" -> {
                    if (!config.areRoomsConfigured()) {
                        throw new InvalidConfigurationException("Rooms are not configured. Please configure rooms first.");
                    }

                    if (tokens.length != 3 || !tokens[1].equalsIgnoreCase("room")) {
                        throw new InvalidCommandException("Unknown or incomplete cancel command: \"" + line + "\"");
                    }

                    int roomId = Integer.parseInt(tokens[2]);
                    if (roomId <= 0) {
                        throw new InvalidConfigurationException("Room ID must be positive.");
                    }
                    return new CancelRoomCommand(roomId);
                }

                case "add" -> {
                    if (!config.areRoomsConfigured()) {
                        throw new InvalidConfigurationException("Rooms are not configured. Please configure rooms first.");
                    }

                    if (tokens.length != 4 || !tokens[1].equalsIgnoreCase("occupant")) {
                        throw new InvalidCommandException("Unknown or incomplete add command: \"" + line + "\"");
                    }

                    int roomId = Integer.parseInt(tokens[2]);
                    int occupants = Integer.parseInt(tokens[3]);

                    if (roomId <= 0) {
                        throw new InvalidConfigurationException("Room ID must be positive.");
                    }

                    if (occupants < 0) {
                        throw new InvalidConfigurationException("Number of occupants cannot be negative.");
                    }

                    return new AddOccupantCommand(roomId, occupants);
                }

                case "room" -> {
                    if (!config.areRoomsConfigured()) {
                        throw new InvalidConfigurationException("Rooms are not configured. Please configure rooms first.");
                    }

                    if (tokens.length != 2 || !tokens[1].equalsIgnoreCase("status")) {
                        throw new InvalidCommandException("Unknown room command: \"" + line + "\"");
                    }

                    return new RoomStatusCommand();
                }

                case "help" -> {
                    return new HelpCommand();
                }

                default ->
                    throw new InvalidCommandException("Unknown command: \"" + line + "\"");
            }
        } catch (NumberFormatException e) {
            throw new InvalidCommandException("Invalid number format in command: \"" + line + "\"");
        }
    }
}
