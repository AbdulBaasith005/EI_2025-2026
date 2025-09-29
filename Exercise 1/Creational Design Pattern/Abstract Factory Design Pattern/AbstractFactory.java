import src.*;

import java.util.Scanner;

public class AbstractFactory {
    public static void main(String[] args) {
        Scanner sc  = new Scanner(System.in);
        System.out.print("1. Google\n2. Alexa\nEnter Choice: ");
        int choice = sc.nextInt();
        SmartHomeFactory factory = null;
        switch (choice) {
            case 1 -> factory = new GoogleFactory();
            case 2 -> factory = new AlexaFactory();
        }
        if (factory == null) return;
        Speaker speaker = factory.createSpeaker();
        Light light = factory.createLight();
        speaker.playSound();
        light.turnOn(speaker.getBrand());
        sc.nextLine();
        System.out.print("Turn OFF light? (Yes/No): ");
        String str = sc.nextLine();
        if (str.equalsIgnoreCase("yes")) light.turnOff(speaker.getBrand());
        else System.exit(0);
    }
}