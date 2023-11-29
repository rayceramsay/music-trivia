package entity;

public class OnlineMP3PlayableAudio implements PlayableAudio {
    private final String audioUrl;
    private boolean isPlaying;

    public OnlineMP3PlayableAudio(String audioUrl) { this.audioUrl = audioUrl; }

    @Override
    public String getPath() { return audioUrl; }

    @Override
    public void play() {
        this.isPlaying = true;
    }

    @Override
    public void stop() {
        this.isPlaying = false;
    }

    @Override
    public boolean isPlaying() {
        return this.isPlaying;
    }

}
