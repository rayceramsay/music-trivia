package interface_adapter.load_game;

import interface_adapter.ViewManagerModel;
import interface_adapter.round.RoundState;
import interface_adapter.round.RoundViewModel;
import use_case.load_game.LoadGameOutputBoundary;
import use_case.load_game.LoadGameOutputData;

/**
 * Implementation of LoadGameOutputBoundary
 */
public class LoadGamePresenter implements LoadGameOutputBoundary {
    private final ViewManagerModel viewManagerModel;
    private final RoundViewModel roundViewModel;
    private final LoadGameViewModel loadGameViewModel;

    /**
     * Constructor to initialize objects of LoadGamePresenter
     *
     * @param viewManagerModel  View manager model
     * @param roundViewModel    View model for round
     * @param loadGameViewModel View model for load game
     */
    public LoadGamePresenter(ViewManagerModel viewManagerModel, RoundViewModel roundViewModel, LoadGameViewModel loadGameViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.roundViewModel = roundViewModel;
        this.loadGameViewModel = loadGameViewModel;
    }

    @Override
    public void prepareView(LoadGameOutputData outputData) {
        RoundState roundState = new RoundState();
        roundState.setGameId(outputData.getGameId());
        roundState.setPromptText(outputData.getQuestion());
        roundState.setCurrentRoundNumber(outputData.getCurrentRoundNumber());
        roundState.setMaxRounds(outputData.getMaxRounds());
        roundState.setCurrentLives(outputData.getCurrentLives());
        roundState.setInitialLives(outputData.getInitialLives());
        roundState.setGenre(outputData.getGenre());
        roundState.setUserAnswer("");
        roundState.setScore(outputData.getScore());


        roundState.setMultipleChoiceOptions(outputData.getMultipleChoiceAnswers());

        roundViewModel.setState(roundState);
        viewManagerModel.setActiveView(roundViewModel.getViewName());
        loadGameViewModel.firePropertyChanged();
        roundViewModel.firePropertyChanged();
        viewManagerModel.firePropertyChanged();
    }
}
