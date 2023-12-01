package entity;

/**
 * Interface for Playable Audio
 */
public interface PlayableAudio {

    /**
     * @return The source path/url of the audio file (i.e. Spotify preview_url).
     */
    String getPath();

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
}
