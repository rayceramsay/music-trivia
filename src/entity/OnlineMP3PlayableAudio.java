package entity;

public class OnlineMP3PlayableAudio implements PlayableAudio{

    private String previewWindow;
    @Override
    public String getPath() {
        return previewWindow;
    }

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
