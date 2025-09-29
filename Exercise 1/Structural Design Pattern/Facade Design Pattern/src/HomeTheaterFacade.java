package src;

// Facade – A single interface that wraps multiple subsystem classes and simplifies their usage.

public class HomeTheaterFacade {
    private Amplifier amp;
    private DVDPlayer dvd;
    private Projector projector;
    private Lights lights;

    public HomeTheaterFacade(Amplifier amp, DVDPlayer dvd, Projector projector, Lights lights) {
        this.amp = amp;
        this.dvd = dvd;
        this.projector = projector;
        this.lights = lights;
    }

    public void watchMovie(String movie) {
        System.out.println("Get ready to watch a movie...");
        lights.dim(20);
        projector.on();
        projector.wideScreenMode();
        amp.on();
        dvd.on();
        dvd.play(movie);
    }

    public void endMovie() {
        System.out.println("Shutting movie theater down...");
        lights.dim(100);
        projector.off();
        amp.off();
        dvd.off();
    }
}
