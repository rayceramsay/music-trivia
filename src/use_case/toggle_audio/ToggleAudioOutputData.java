package use_case.toggle_audio;

public class ToggleAudioOutputData {
    private final boolean isAudioPaused;
    private final boolean isAudioPlaying;

    public ToggleAudioOutputData(boolean isAudioPaused, boolean isAudioPlaying) {
        this.isAudioPaused = isAudioPaused;
        this.isAudioPlaying = isAudioPlaying;
    }

    public boolean isAudioPaused() {
        return isAudioPaused;
    }

    public boolean isAudioPlaying() {
        return isAudioPlaying;
    }
}
