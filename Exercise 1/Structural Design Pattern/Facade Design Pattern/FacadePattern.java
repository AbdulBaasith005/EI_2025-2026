import src.*;

public class FacadePattern {
    public static void main(String[] args) {
        Amplifier amp = new Amplifier();
        DVDPlayer dvd = new DVDPlayer();
        Projector projector = new Projector();
        Lights lights = new Lights();

        HomeTheaterFacade homeTheater = new HomeTheaterFacade(amp, dvd, projector, lights);

        homeTheater.watchMovie("Inception");
        System.out.println();
        homeTheater.endMovie();
    }
}
