package src;


// Subject (Publisher/Observable) - Maintains list of observers and notifies them when its state changes.

public interface Subject {
    void addObserver(Observer observer);
    void removeObserver(Observer observer);
    void notifyObservers();
}
