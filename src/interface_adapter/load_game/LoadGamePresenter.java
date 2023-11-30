package interface_adapter.load_game;

import interface_adapter.ViewManagerModel;
import interface_adapter.round.RoundState;
import interface_adapter.round.RoundViewModel;
import use_case.load_game.LoadGameOutputBoundary;
import use_case.load_game.LoadGameOutputData;

public class LoadGamePresenter implements LoadGameOutputBoundary {
    private final ViewManagerModel viewManagerModel;
    private final RoundViewModel roundViewModel;
    private final LoadGameViewModel loadGameViewModel;

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


        if (outputData.getDifficulty().equalsIgnoreCase("easy")) {
            roundState.setEasyRound(true);
            roundState.setMediumRound(false);
            roundState.setHardRound(false);
            roundState.setMultipleChoice1(outputData.getMultipleChoiceAnswers().get(0));
            roundState.setMultipleChoice2(outputData.getMultipleChoiceAnswers().get(1));
        }
        else if (outputData.getDifficulty().equalsIgnoreCase("Medium")) {
            roundState.setEasyRound(false);
            roundState.setMediumRound(true);
            roundState.setHardRound(false);
            roundState.setMultipleChoice1(outputData.getMultipleChoiceAnswers().get(0));
            roundState.setMultipleChoice2(outputData.getMultipleChoiceAnswers().get(1));
            roundState.setMultipleChoice3(outputData.getMultipleChoiceAnswers().get(2));
            roundState.setMultipleChoice4(outputData.getMultipleChoiceAnswers().get(3));
        }
        else if (outputData.getDifficulty().equalsIgnoreCase("Hard")) {
            roundState.setEasyRound(false);
            roundState.setMediumRound(false);
            roundState.setHardRound(true);
        }

        roundViewModel.setState(roundState);
        viewManagerModel.setActiveView(roundViewModel.getViewName());
        loadGameViewModel.firePropertyChanged();
        roundViewModel.firePropertyChanged();
        viewManagerModel.firePropertyChanged();
    }
}
