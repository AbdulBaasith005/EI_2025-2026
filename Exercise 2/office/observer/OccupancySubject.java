package office.observer;

public interface OccupancySubject {
    void addObserver(OccupancyObserver observer);
    void removeObserver(OccupancyObserver observer);
    void notifyObservers(boolean occupied);
}
