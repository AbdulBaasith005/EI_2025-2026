package src;

public class AlexaFactory implements SmartHomeFactory {
    @Override
    public Speaker createSpeaker() {
        return new AlexaSpeaker();
    }

    @Override
    public Light createLight() {
        return new AlexaLight();
    }
}
