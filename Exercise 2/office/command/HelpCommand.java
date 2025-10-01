package office.command;

public class HelpCommand implements Command {
    @Override
    public void execute() {
        System.out.println("=== Smart Office Commands ===");
        System.out.println("config room count <number>                  : Configure total number of rooms");
        System.out.println("config room max capacity <id> <capacity>    : Set room max capacity");
        System.out.println("book room <id> <HH:MM> <duration>           : Book a room");
        System.out.println("cancel room <id>                            : Cancel a room booking");
        System.out.println("add occupant <id> <number>                  : Add occupants to a room");
        System.out.println("room status                                 : Show status of all rooms");
        System.out.println("help                                        : Show this menu");
        System.out.println("Exit                                        : Quit the application");
    }
}
