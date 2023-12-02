package entity;

import java.util.function.Consumer;

/**
 * Interface for Playable Audio
 */
public interface PlayableAudio {
    /**
     * @return The source path/url of the audio file (i.e. Spotify preview_url).
     */
    String getPath(); // e.g. Spotify preview_url or file location

    /**
     * Play song.
     */
    void play();

    /**
     * Stop playing song.
     */
    void stop();

    /**
     * @return if song is currently playing.
     */
    boolean isPlaying();

    void setOnStopCallback(Consumer<Void> callback);
}
