package use_case.load_game;

import data_access.game_data.GameDataAccessInterface;
import entity.Game;

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
        LoadGameOutputData outputData = new LoadGameOutputData(gameToLoad.getID(),
                gameToLoad.getCurrentRound().getQuestion(),
                gameToLoad.getGenre(),
                gameToLoad.getInitialLives(),
                gameToLoad.getCurrentLives(),
                gameToLoad.getMaxRounds(),
                gameToLoad.getRoundsPlayed());
        presenter.prepareView(outputData);
    }
}
