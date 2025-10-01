package office.observer;

public interface OccupancyObserver {
    void update(int roomId, boolean occupied);
}
