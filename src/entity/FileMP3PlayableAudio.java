package entity;

public class FileMP3PlayableAudio implements PlayableAudio {
    private final String audioFilePath;

    public FileMP3PlayableAudio(String audioFilePath) { this.audioFilePath = audioFilePath; }

    @Override
    public String getPath() { return audioFilePath; }

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
