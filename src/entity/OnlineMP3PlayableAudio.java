package entity;

/**
 * Implementation of PlayableSong from online source (i.e. Spotify API)
 */
public class OnlineMP3PlayableAudio implements PlayableAudio {
    private final String audioUrl;
    private boolean isPlaying;

    /**
     * Constructor to initialize objects of OnlineMP3PlayableAudio
     *
     * @param audioUrl URL of audio source
     */
    public OnlineMP3PlayableAudio(String audioUrl) {
        this.audioUrl = audioUrl;
    }

    /**
     * @return URL of audio.
     */
    @Override
    public String getPath() {
        return audioUrl;
    }

    /**
     * Start playing song audio.
     */
    @Override
    public void play() {
        this.isPlaying = true;
    }

    /**
     * Stop playing song audio.
     */
    @Override
    public void stop() {
        this.isPlaying = false;
    }

    /**
     * @return check if song audio is currently playing and output boolean value.
     */
    @Override
    public boolean isPlaying() {
        return this.isPlaying;
    }

}
