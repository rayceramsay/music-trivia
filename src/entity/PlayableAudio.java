package entity;

import java.util.function.Consumer;

/**
 * Represents a playable audio source (e.g. from the Spotify API)
 */
public interface PlayableAudio {
    String getPath(); // e.g. Spotify preview_url or file location
    void play();
    void stop();
    boolean isPlaying();
    void setOnStopCallback(Consumer<Void> callback);
}
