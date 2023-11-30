package interface_adapter.toggle_audio;

import interface_adapter.round.RoundViewModel;
import use_case.toggle_audio.ToggleAudioOutputBoundary;

/**
 * Implementation of ToggleAudioOutputBoundary
 */
public class ToggleAudioPresenter implements ToggleAudioOutputBoundary {
    private final ToggleAudioViewModel toggleAudioViewModel;
    private final RoundViewModel roundViewModel;

    /**
     * Constructor to initialize objects of ToggleAudioPresenter
     *
     * @param toggleAudioViewModel View model for toggle audio interface interface
     * @param roundViewModel       View model for Round
     */
    public ToggleAudioPresenter(ToggleAudioViewModel toggleAudioViewModel, RoundViewModel roundViewModel) {
        this.toggleAudioViewModel = toggleAudioViewModel;
        this.roundViewModel = roundViewModel;
    }

    @Override
    public void showPauseButton() {
        ToggleAudioState toggleAudioState = toggleAudioViewModel.getState();
        toggleAudioState.setImgPath("src/assets/pause-img2.png");
        toggleAudioViewModel.firePropertyChanged();
        roundViewModel.firePropertyChanged();

    }

    @Override
    public void showPlayButton() {
        ToggleAudioState toggleAudioState = toggleAudioViewModel.getState();
        toggleAudioState.setImgPath("src/assets/play-img2.png");
        toggleAudioViewModel.firePropertyChanged();
        roundViewModel.firePropertyChanged();
    }
}
