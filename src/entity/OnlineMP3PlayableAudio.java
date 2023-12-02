package entity;

import javafx.embed.swing.JFXPanel;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

import java.util.function.Consumer;

/**
 * Implementation of PlayableSong from online source (i.e. Spotify API)
 */
public class OnlineMP3PlayableAudio implements PlayableAudio {

    private final static JFXPanel JFX_PANEL = new JFXPanel(); // required for MediaPlayer to work

    private final String audioUrl;
    private final MediaPlayer mediaPlayer;

    /**
     * Constructor to initialize objects of OnlineMP3PlayableAudio
     *
     * @param audioUrl URL of audio source
     */
    public OnlineMP3PlayableAudio(String audioUrl) {
        this.audioUrl = audioUrl;

        Media audioMedia = new Media(audioUrl);
        mediaPlayer = new MediaPlayer(audioMedia);
        mediaPlayer.setStopTime(new Duration(10000));
        mediaPlayer.setOnEndOfMedia(mediaPlayer::stop);  // fixes bug where stopped audio after setStopTime duration still registers as playing
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
        mediaPlayer.play();
    }

    /**
     * Stop playing song audio.
     */
    @Override
    public void stop() {
        mediaPlayer.stop();
    }

    /**
     * @return check if song audio is currently playing and output boolean value.
     */
    @Override
    public boolean isPlaying() {
        return mediaPlayer.getStatus().equals(MediaPlayer.Status.PLAYING);
    }

    @Override
    public void setOnStopCallback(Consumer<Void> callback) {
        mediaPlayer.setOnEndOfMedia(() -> {
            mediaPlayer.stop();
            callback.accept(null);
        });
    }
}
