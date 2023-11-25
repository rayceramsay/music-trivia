package interface_adapter.toggle_audio;

import interface_adapter.round.RoundViewModel;
import use_case.toggle_audio.ToggleAudioOutputBoundary;
import use_case.toggle_audio.ToggleAudioOutputData;

public class ToggleAudioPresenter implements ToggleAudioOutputBoundary {
    private final ToggleAudioViewModel toggleAudioViewModel;
    private final RoundViewModel roundViewModel;

    public ToggleAudioPresenter(ToggleAudioViewModel toggleAudioViewModel, RoundViewModel roundViewModel) {
        this.toggleAudioViewModel = toggleAudioViewModel;
        this.roundViewModel = roundViewModel;
    }

    @Override
    public void preparePauseButton(ToggleAudioOutputData outputData) {
        ToggleAudioState toggleAudioState = toggleAudioViewModel.getState();
        if (outputData.isAudioPlaying()) { //button should be pause when something is playing
            toggleAudioState.setImgPath("pause-img.png");
            toggleAudioViewModel.setState(toggleAudioState);
        }
        else {
            toggleAudioState.setImgPath("play-img.png");
            toggleAudioViewModel.setState(toggleAudioState);
        }
        toggleAudioViewModel.firePropertyChanged();
        roundViewModel.firePropertyChanged();

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
        roundViewModel.firePropertyChanged();
    }
}
