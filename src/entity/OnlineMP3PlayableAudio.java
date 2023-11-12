package entity;

public class OnlineMP3PlayableAudio implements PlayableAudio{
    private final String audioUrl;

    public OnlineMP3PlayableAudio(String audioUrl) { this.audioUrl = audioUrl; }

    @Override
    public String getPath() { return audioUrl; }

    @Override
    public void play() {

    }

    @Override
    public void stop() {

    }

    @Override
    public boolean isPlaying() {
        return false;
    }
}
