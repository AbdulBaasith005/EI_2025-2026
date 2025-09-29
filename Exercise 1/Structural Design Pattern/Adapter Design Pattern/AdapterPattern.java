import src.AudioPlayer;

public class AdapterPattern {
    public static void main(String[] args) {
        AudioPlayer audioPlayer = new AudioPlayer();
        
        audioPlayer.play("mp3", "song1.mp3");
        audioPlayer.play("mp4", "video1.mp4");
        audioPlayer.play("vlc", "movie1.vlc");
        audioPlayer.play("avi", "myMovie.avi");
    }
}
