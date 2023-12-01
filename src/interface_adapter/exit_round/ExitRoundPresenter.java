package interface_adapter.exit_round;

import interface_adapter.ViewManagerModel;
import interface_adapter.round.RoundState;
import interface_adapter.round.RoundViewModel;
import interface_adapter.toggle_audio.ToggleAudioState;
import interface_adapter.toggle_audio.ToggleAudioViewModel;
import use_case.exit_round.ExitRoundOutputBoundary;

public class ExitRoundPresenter implements ExitRoundOutputBoundary {

    private final ViewManagerModel viewManagerModel;
    private final ExitRoundViewModel exitRoundViewModel;
    private final RoundViewModel roundViewModel;
    private final ToggleAudioViewModel toggleAudioViewModel;

    public ExitRoundPresenter(ViewManagerModel viewManagerModel, ExitRoundViewModel exitRoundViewModel,
                              RoundViewModel roundViewModel, ToggleAudioViewModel toggleAudioViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.exitRoundViewModel = exitRoundViewModel;
        this.roundViewModel = roundViewModel;
        this.toggleAudioViewModel = toggleAudioViewModel;
    }

    @Override
    public void prepareView() {
        ToggleAudioState toggleAudioState = toggleAudioViewModel.getState();
        toggleAudioState.setImgPath(toggleAudioViewModel.getPlayButtonImagePath());

        RoundState roundState = roundViewModel.getState();
        roundState.setUserAnswer("");

        viewManagerModel.setActiveView(exitRoundViewModel.getViewName());

        roundViewModel.firePropertyChanged();
        toggleAudioViewModel.firePropertyChanged();
        viewManagerModel.firePropertyChanged();
    }
}
