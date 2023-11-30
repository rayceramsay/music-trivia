package interface_adapter.create_game;

import interface_adapter.ViewManagerModel;
import interface_adapter.round.RoundState;
import interface_adapter.round.RoundViewModel;
import use_case.create_game.CreateGameOutputBoundary;
import use_case.create_game.CreateGameOutputData;
import view.RoundView;

public class CreateGamePresenter implements CreateGameOutputBoundary {

    private final ViewManagerModel viewManagerModel;
    private final RoundViewModel roundViewModel;
    private final CreateGameViewModel createGameViewModel;

    public CreateGamePresenter (ViewManagerModel viewManagerModel, RoundViewModel roundViewModel, CreateGameViewModel createGameViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.roundViewModel = roundViewModel;
        this.createGameViewModel = createGameViewModel;
    }

    @Override
    public void prepareFirstRoundView(CreateGameOutputData createGameOutputData) {

        RoundState roundState = roundViewModel.getState();

        roundState.setGameId(createGameOutputData.getGameId());

        roundState.setCurrentRoundNumber(1); // assuming counting starts at 1
        roundState.setMaxRounds(createGameOutputData.getRounds());
        roundState.setGenre(createGameOutputData.getGenre());
        roundState.setCurrentLives(createGameOutputData.getLives());
        roundState.setInitialLives(createGameOutputData.getLives());

        if (createGameOutputData.getDifficulty().equalsIgnoreCase("easy")) {
            roundState.setEasyRound(true);
            roundState.setMediumRound(false);
            roundState.setHardRound(false);
            roundState.setMultipleChoice1(createGameOutputData.getMultipleChoiceAnswers().get(0));
            roundState.setMultipleChoice2(createGameOutputData.getMultipleChoiceAnswers().get(1));
        }
        else if (createGameOutputData.getDifficulty().equalsIgnoreCase("Medium")) {
            roundState.setEasyRound(false);
            roundState.setMediumRound(true);
            roundState.setHardRound(false);
            roundState.setMultipleChoice1(createGameOutputData.getMultipleChoiceAnswers().get(0));
            roundState.setMultipleChoice2(createGameOutputData.getMultipleChoiceAnswers().get(1));
            roundState.setMultipleChoice3(createGameOutputData.getMultipleChoiceAnswers().get(2));
            roundState.setMultipleChoice4(createGameOutputData.getMultipleChoiceAnswers().get(3));
        }
        else if (createGameOutputData.getDifficulty().equalsIgnoreCase("Hard")) {
            roundState.setEasyRound(false);
            roundState.setMediumRound(false);
            roundState.setHardRound(true);
        }

        viewManagerModel.setActiveView(roundViewModel.getViewName());

        createGameViewModel.firePropertyChanged();
        roundViewModel.firePropertyChanged();
        viewManagerModel.firePropertyChanged();
    }
}
