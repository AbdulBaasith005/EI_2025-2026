import src.*;

// Client - Sets up the subject and observers, and simulates state changes.

public class ObserverPattern {
    public static void main(String[] args) {
        WeatherStation weatherStation = new WeatherStation();
        Observer mobileApp = new MobileApp();
        Observer webApp = new WebApp();

        weatherStation.addObserver(mobileApp);
        weatherStation.addObserver(webApp);

        weatherStation.setTemperature(25.3f);
        weatherStation.setTemperature(30.5f);
    }
}
