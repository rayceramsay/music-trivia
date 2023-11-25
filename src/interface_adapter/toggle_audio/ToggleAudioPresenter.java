package interface_adapter.toggle_audio;

import interface_adapter.round.RoundViewModel;
import use_case.toggle_audio.ToggleAudioOutputBoundary;

public class ToggleAudioPresenter implements ToggleAudioOutputBoundary {
    private final ToggleAudioViewModel toggleAudioViewModel;
    private final RoundViewModel roundViewModel;

    public ToggleAudioPresenter(ToggleAudioViewModel toggleAudioViewModel, RoundViewModel roundViewModel) {
        this.toggleAudioViewModel = toggleAudioViewModel;
        this.roundViewModel = roundViewModel;
    }

    @Override
    public void showPauseButton() {
        ToggleAudioState toggleAudioState = toggleAudioViewModel.getState();

        toggleAudioState.setImgPath("pause-img.png");
        toggleAudioViewModel.firePropertyChanged();
        roundViewModel.firePropertyChanged();

    }

    @Override
    public void showPlayButton() {
        ToggleAudioState toggleAudioState = toggleAudioViewModel.getState();
        toggleAudioState.setImgPath("play-img.png");
        toggleAudioViewModel.firePropertyChanged();
        roundViewModel.firePropertyChanged();
    }
}
