package use_case.toggle_audio;

public interface ToggleAudioOutputBoundary {
    public void preparePauseView(ToggleAudioOutputData outputData);
    public void preparePlayView(ToggleAudioOutputData outputData);
}
