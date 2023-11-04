package entity;

public class FileMP3PlayableAudio implements PlayableAudio {

    private String fileLocation;
    @Override
    public String getPath() {
        return fileLocation;
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
