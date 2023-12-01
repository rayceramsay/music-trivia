package entity;

import java.util.function.Consumer;

public interface PlayableAudio {
    String getPath(); // e.g. Spotify preview_url or file location
    void play();
    void stop();
    boolean isPlaying();
    void setOnStopCallback(Consumer<Void> callback);
}
