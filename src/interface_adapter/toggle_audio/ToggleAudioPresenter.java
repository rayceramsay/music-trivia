package interface_adapter.toggle_audio;

import use_case.toggle_audio.ToggleAudioOutputBoundary;
import use_case.toggle_audio.ToggleAudioOutputData;

public class ToggleAudioPresenter implements ToggleAudioOutputBoundary {
    private final ToggleAudioViewModel toggleAudioViewModel;

    public ToggleAudioPresenter(ToggleAudioViewModel toggleAudioViewModel) {
        this.toggleAudioViewModel = toggleAudioViewModel;
    }

    @Override
    public void preparePauseButton(ToggleAudioOutputData outputData) {
        ToggleAudioState toggleAudioState = toggleAudioViewModel.getState();
        if (outputData.isAudioPlaying()) { //button should be pause when something is playing
            toggleAudioState.setImgPath("pause-img.png");
        }
        else {
            toggleAudioState.setImgPath("play-img.png");
        }
        toggleAudioViewModel.firePropertyChanged();
    }

    @Override
    public void preparePlayButton(ToggleAudioOutputData outputData) {
        ToggleAudioState toggleAudioState = toggleAudioViewModel.getState();
        if (!outputData.isAudioPlaying()) { //button should be play whe nothing is playing
            toggleAudioState.setImgPath("play-img.png");
        }
        else {
            toggleAudioState.setImgPath("pause-img.png");
        }
        toggleAudioViewModel.firePropertyChanged();
    }
}
