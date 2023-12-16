package entity;

import java.util.function.Consumer;

public class MockPlayableAudio implements PlayableAudio {

    private final String audioUrl;
    private boolean isPlaying;
    private Consumer<Void> onStopCallback;

    public MockPlayableAudio(String audioUrl) { this.audioUrl = audioUrl; }

    @Override
    public String getPath() { return audioUrl; }

    @Override
    public void play() {
        this.isPlaying = true;
    }

    @Override
    public void stop() {
        this.isPlaying = false;

        if (onStopCallback != null) {
            onStopCallback.accept(null);
        }
    }

    @Override
    public boolean isPlaying() {
        return this.isPlaying;
    }

    @Override
    public void setOnStopCallback(Consumer<Void> callback) {
        this.onStopCallback = callback;
    }
}
