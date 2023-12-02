package entity;

import java.util.function.Consumer;

/**
 * Implementation to be used for testing purposes so that when OnlineMP3PlayableAudio is implemented, existing tests don't stop failing
 * Would fail due to having a filler url in the test and not the real one
 */
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
