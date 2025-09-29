package src;

// Observer (Subscriber/Listener) - Defines the update() method to react when subject notifies.

public interface Observer {
    void update(float temperature);
}
