package interface_adapter.finish_round;

import interface_adapter.ViewManagerModel;
import interface_adapter.game_over.GameOverState;
import interface_adapter.game_over.GameOverViewModel;
import interface_adapter.round.RoundState;
import interface_adapter.round.RoundViewModel;
import use_case.finish_round.FinishRoundOutputBoundary;
import use_case.finish_round.FinishRoundOutputData;

/**
 * Presenter which implements the Output Boundary for the FinishRound interface adapter
 */
public class FinishRoundPresenter implements FinishRoundOutputBoundary {
    private final ViewManagerModel viewManagerModel;
    private final GameOverViewModel gameOverViewModel;
    private final RoundViewModel roundViewModel;

    /**
     * Constructor to initialize objects of FinishRoundPresenter
     *
     * @param viewManagerModel  View manager model
     * @param gameOverViewModel View model for game over state
     * @param roundViewModel    View model for a round
     */
    public FinishRoundPresenter(ViewManagerModel viewManagerModel,
                                GameOverViewModel gameOverViewModel,
                                RoundViewModel roundViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.gameOverViewModel = gameOverViewModel;
        this.roundViewModel = roundViewModel;
    }

    /**
     * This method will set the state using gameOverViewModel, provided the final round of the game was just played.
     * It will also set the final score of the game
     *
     * @param outputData Output data for FinishRound use case
     */
    @Override
    public void prepareGameOverView(FinishRoundOutputData outputData) {
        GameOverState state = this.gameOverViewModel.getState();
        state.setScore(outputData.getScore());
        this.gameOverViewModel.setState(state);

        viewManagerModel.setActiveView(gameOverViewModel.getViewName());
        gameOverViewModel.firePropertyChanged();
        viewManagerModel.firePropertyChanged();
    }

    /**
     * This method will set the state using roundViewModel, provided it is not the last round of the game.
     * It will then add one to the current round number signifying that the previous one has just been finished.
     * And set the genre, amount of lives left, as well as reset the user answer to empty string for the next round
     *
     * @param outputData Output data for the FinishRound use case
     */
    @Override
    public void prepareNextRoundView(FinishRoundOutputData outputData) {
        RoundState roundState = this.roundViewModel.getState();
        roundState.setCurrentRoundNumber(roundState.getCurrentRoundNumber() + 1);
        roundState.setGenre(outputData.getGenre());
        roundState.setCurrentLives(outputData.getLives());
        roundState.setUserAnswer("");
        this.roundViewModel.setState(roundState);

        roundViewModel.firePropertyChanged();
    }
}
