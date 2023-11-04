package entity;

public interface PlayableAudio {

    String getPath(); // preview_window or file location

    void play();
    void stop();
    boolean isPlaying();

}
