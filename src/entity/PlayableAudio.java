package entity;

public interface PlayableAudio {

    String getPath(); // e.g. Spotify preview_url or file location
    void play();
    void stop();
    boolean isPlaying();

}
