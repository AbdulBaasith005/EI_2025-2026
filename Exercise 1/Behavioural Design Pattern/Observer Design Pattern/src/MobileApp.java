package src;

// Concrete Observer - Implements how each observer responds to updates from the subject.

public class MobileApp implements Observer {
    @Override
    public void update(float temperature) {
        System.out.println("Mobile App: New Temperature = " + temperature + "°C");
    }
}
