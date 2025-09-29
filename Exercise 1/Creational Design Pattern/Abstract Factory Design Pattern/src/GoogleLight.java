package src;

public class GoogleLight implements Light {
    @Override
    public void turnOn(String brand) {
        System.out.println(brand + " Light: Turning ON...");
    }

    @Override
    public void turnOff(String brand) {
        System.out.println(brand + " Light: Turning OFF...");
    }
}
