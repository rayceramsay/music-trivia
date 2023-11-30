package entity;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.embed.swing.JFXPanel;
import javafx.util.Duration;

public class OnlineMP3PlayableAudio implements PlayableAudio {

    private final static JFXPanel JFX_PANEL = new JFXPanel(); // required for MediaPlayer to work

    private final String audioUrl;
    private final MediaPlayer mediaPlayer;

    public OnlineMP3PlayableAudio(String audioUrl) {
        this.audioUrl = audioUrl;

        Media audioMedia = new Media(audioUrl);
        mediaPlayer = new MediaPlayer(audioMedia);
        mediaPlayer.setStopTime(new Duration(10000));
        mediaPlayer.setOnEndOfMedia(mediaPlayer::stop);  // fixes bug where stopped audio after setStopTime duration still registers as playing
    }

    @Override
    public String getPath() {
        return audioUrl;
    }

    @Override
    public void play() {
        mediaPlayer.play();
    }

    @Override
    public void stop() {
        mediaPlayer.stop();
    }

    @Override
    public boolean isPlaying() {
        return mediaPlayer.getStatus().equals(MediaPlayer.Status.PLAYING);
    }
}
