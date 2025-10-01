package office.observer;

import java.util.logging.Level;
import java.util.logging.Logger;
import office.log.LoggerFactory;

public class LightSystem implements OccupancyObserver {
    private static final Logger logger = LoggerFactory.getLogger();

    @Override
    public void update(int roomId, boolean occupied) {
        if (occupied) {
            System.out.println("Lights in Room " + roomId + " turned ON.");
            logger.log(Level.INFO, () -> "Lights in Room " + roomId + " turned ON.");
        } else {
            System.out.println("Lights in Room " + roomId + " turned OFF.");
            logger.log(Level.INFO, () -> "Lights in Room " + roomId + " turned OFF.");
        }
    }
}
