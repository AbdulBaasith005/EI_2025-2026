package src;

// Concrete Observer - Implements how each observer responds to updates from the subject.

public class WebApp implements Observer {
    @Override
    public void update(float temperature) {
        System.out.println("Web App: New Temperature = " + temperature + "°C");
    }
}
