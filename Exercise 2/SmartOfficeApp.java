import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import office.command.*;
import office.config.OfficeConfig;
import office.exception.*;
import office.log.LoggerFactory;

public class SmartOfficeApp {

    private static final Logger logger = LoggerFactory.getLogger();

    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            CommandInvoker invoker = new CommandInvoker();
            OfficeConfig config = OfficeConfig.getInstance();

            System.out.println("Welcome to Smart Office. Enter commands (type 'help' to list all valid commands, 'Exit' to quit).");
            logger.log(Level.INFO, "SmartOffice application started.");

            while (true) {
                System.out.print("smartoffice> ");
                String line = sc.nextLine().trim();

                if (line.equalsIgnoreCase("Exit")) {
                    System.out.println("Exiting Smart Office...");
                    logger.log(Level.INFO, "SmartOffice application shutting down.");
                    config.shutdown();
                    break;
                }

                executeCommand(line, invoker);
            }
        }
    }

    private static void executeCommand(String line, CommandInvoker invoker) {
        try {
            Command cmd = CommandParser.parse(line);
            invoker.execute(cmd);
            System.out.println();
        } catch (InvalidCommandException |
                 RoomNotFoundException |
                 RoomAlreadyBookedException |
                 RoomNotBookedException |
                 RoomOccupiedException |
                 CapacityExceededException |
                 InvalidConfigurationException |
                 InvalidTimeException e) {
            System.out.println("Error: " + e.getMessage() + "\n");
            logger.log(Level.WARNING, "Execution error: {0}", e.getMessage());
            System.out.println();
        } catch (Exception e) {
            System.out.println("Unexpected error: " + e.getMessage() + "\n");
            logger.log(Level.SEVERE, "Unexpected system error while processing: " + line, e);
        }
    }
}
