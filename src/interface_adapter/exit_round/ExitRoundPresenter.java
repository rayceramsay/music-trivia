package interface_adapter.exit_round;

import interface_adapter.ViewManagerModel;
import interface_adapter.round.RoundState;
import interface_adapter.round.RoundViewModel;
import interface_adapter.toggle_audio.ToggleAudioState;
import interface_adapter.toggle_audio.ToggleAudioViewModel;
import use_case.exit_round.ExitRoundOutputBoundary;

/**
 * Presenter which implements the Output Boundary for the ExitRound interface adapter
 */
public class ExitRoundPresenter implements ExitRoundOutputBoundary {

    private final ViewManagerModel viewManagerModel;
    private final ExitRoundViewModel exitRoundViewModel;
    private final RoundViewModel roundViewModel;
    private final ToggleAudioViewModel toggleAudioViewModel;

    /**
     * Constructor to initialize objects of ExitRoundPresenter
     *
     * @param viewManagerModel     View manager model
     * @param exitRoundViewModel   View model for ExitRound
     * @param roundViewModel       View model for Round
     * @param toggleAudioViewModel View model for ToggleAudio
     */
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
