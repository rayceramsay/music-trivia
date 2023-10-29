package entity;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.advanced.AdvancedPlayer;

import java.io.IOException;
import java.io.InputStream;

public class MP3SongPlayer implements SongPlayer {
    private AdvancedPlayer player;
    private Thread playbackThread;

    @Override
    public void playAudio(AudioSource mp3AudioSource) {
        try {
            InputStream audioInputStream = mp3AudioSource.getAudioStream();
            this.player = new AdvancedPlayer(audioInputStream);
            this.playbackThread = new Thread(() -> {
                try {
                    this.player.play(msToFrames(10000)); // 10 secs
                } catch (JavaLayerException e) {
                    throw new RuntimeException(e);
                }
            });
            this.playbackThread.start();
        } catch (IOException | JavaLayerException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void stopAudio() {
        if (this.player != null && playbackThread != null && playbackThread.isAlive()) {
            this.player.close();
            this.playbackThread.interrupt();
        }
    }

    private int msToFrames(int ms) {
        int msPerFrame = 26;
        return ms / msPerFrame;
    }
}
