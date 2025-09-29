package src;

public class AlexaSpeaker implements Speaker {
    @Override
    public void playSound() {
        System.out.println(this.getBrand() + " Speaker: Playing Sound...");
    }

    @Override
    public String getBrand() {
        return "Alexa";
    }
}
