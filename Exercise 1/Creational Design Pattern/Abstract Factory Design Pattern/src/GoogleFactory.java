package src;

public class GoogleFactory implements SmartHomeFactory {
    @Override
    public Speaker createSpeaker() {
        return new GoogleSpeaker();
    }

    @Override
    public Light createLight() {
        return new GoogleLight();
    }
}
