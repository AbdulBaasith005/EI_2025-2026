package src;

import java.util.ArrayList;
import java.util.List;

// Concrete Subject - Actual object with state; calls notifyObservers() whenever state changes.

public class WeatherStation implements Subject {
    private float temperature;
    private List<Observer> observers = new ArrayList<>();

    public void setTemperature(float temperature) {
        this.temperature = temperature;
        notifyObservers();
    }

    @Override
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (Observer o : observers) {
            o.update(temperature);
        }
    }
}
