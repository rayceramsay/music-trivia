package interface_adapter.create_game;

import interface_adapter.ViewManagerModel;
import interface_adapter.round.RoundState;
import interface_adapter.round.RoundViewModel;
import use_case.create_game.CreateGameOutputBoundary;
import use_case.create_game.CreateGameOutputData;

/**
 * Presenter which implements the Output Boundary for the CreateGame interface adapter
 */
public class CreateGamePresenter implements CreateGameOutputBoundary {

    private final ViewManagerModel viewManagerModel;
    private final RoundViewModel roundViewModel;
    private final CreateGameViewModel createGameViewModel;

    /**
     * Constructor to initialize objects of CreateGamePresenter
     *
     * @param viewManagerModel View Manager Model
     * @param roundViewModel   View Model for a round
     */
    public CreateGamePresenter(ViewManagerModel viewManagerModel, RoundViewModel roundViewModel, CreateGameViewModel createGameViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.roundViewModel = roundViewModel;
        this.createGameViewModel = createGameViewModel;
    }

    /**
     * Sets the starting round using data such as GameID, MaxRounds, Genre, Initial lives from output data
     *
     * @param createGameOutputData Output Data for CreateGame use case
     */
    @Override
    public void prepareFirstRoundView(CreateGameOutputData createGameOutputData) {

        RoundState roundState = roundViewModel.getState();

        roundState.setGameId(createGameOutputData.getGameId());

        roundState.setCurrentRoundNumber(1); // assuming counting starts at 1
        roundState.setMaxRounds(createGameOutputData.getRounds());
        roundState.setGenre(createGameOutputData.getGenre());
        roundState.setCurrentLives(createGameOutputData.getLives());
        roundState.setInitialLives(createGameOutputData.getLives());
        roundState.setScore(0);

        roundState.setMultipleChoiceOptions(createGameOutputData.getMultipleChoiceAnswers());

        viewManagerModel.setActiveView(roundViewModel.getViewName());

        createGameViewModel.firePropertyChanged();
        roundViewModel.firePropertyChanged();
        viewManagerModel.firePropertyChanged();
    }
}
