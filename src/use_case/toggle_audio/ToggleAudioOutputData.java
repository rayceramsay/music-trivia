package use_case.toggle_audio;

public class ToggleAudioOutputData {
    //private final boolean isAudioPaused;
    private final boolean isAudioPlaying;

    public ToggleAudioOutputData(boolean isAudioPlaying) {
        this.isAudioPlaying = isAudioPlaying;
    }

    public boolean isAudioPlaying() {
        return isAudioPlaying;
    }
}
