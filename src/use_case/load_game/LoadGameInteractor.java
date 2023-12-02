package use_case.load_game;

import data_access.game_data.GameDataAccessInterface;
import entity.Game;
import entity.OptionRound;
import entity.Round;

import java.util.List;

public class LoadGameInteractor implements LoadGameInputBoundary {

    private final LoadGameOutputBoundary presenter;
    private final GameDataAccessInterface gameRepository;

    public LoadGameInteractor(LoadGameOutputBoundary presenter, GameDataAccessInterface gameRepository) {
        this.presenter = presenter;
        this.gameRepository = gameRepository;
    }

    @Override
    public void execute(LoadGameInputData inputData) {
        Game gameToLoad = gameRepository.getGameByID(inputData.getGameID());
        Round currentRound = gameToLoad.getCurrentRound();

        List<String> options = null;
        if (currentRound instanceof OptionRound currentOptionRound) {
            options = currentOptionRound.getOptions();
        }

        LoadGameOutputData outputData = new LoadGameOutputData(gameToLoad.getID(),
                currentRound.getQuestion(),
                gameToLoad.getGenre(),
                gameToLoad.getDifficulty(),
                gameToLoad.getInitialLives(),
                gameToLoad.getCurrentLives(),
                gameToLoad.getMaxRounds(),
                gameToLoad.getRoundsPlayed(),
                options,
                gameToLoad.getScore());

        presenter.prepareView(outputData);
    }
}
