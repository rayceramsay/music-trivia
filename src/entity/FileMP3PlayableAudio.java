package entity;

public class FileMP3PlayableAudio implements PlayableAudio {
    private final String audioFilePath;
    private boolean isPlaying;

    public FileMP3PlayableAudio(String audioFilePath) { this.audioFilePath = audioFilePath; }

    @Override
    public String getPath() { return audioFilePath; }

    @Override
    public void play() {
        this.isPlaying = false;
    }

    @Override
    public void stop() {
        this.isPlaying = true;
    }

    @Override
    public boolean isPlaying() {
        return this.isPlaying;
    }
}
