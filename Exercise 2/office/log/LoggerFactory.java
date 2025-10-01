package office.log;

import java.io.IOException;
import java.util.logging.*;

public class LoggerFactory {
    private static final Logger logger = Logger.getLogger("SmartOfficeLogger");

    static {
        try {
            FileHandler fh = new FileHandler("office/log/smartoffice.log", true);
            fh.setLevel(Level.ALL);
            fh.setFormatter(new SimpleFormatter());
            logger.addHandler(fh);

            ConsoleHandler ch = new ConsoleHandler();
            ch.setLevel(Level.ALL);
            ch.setFormatter(new SimpleFormatter());
            logger.addHandler(ch);

            logger.setUseParentHandlers(false);
            logger.setLevel(Level.ALL);

        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    public static Logger getLogger() {
        return logger;
    }
}
