package use_case.load_game;

import data_access.game_data.GameDataAccessInterface;
import entity.Game;
import entity.Round;

/**
 * Interactor which implements the Input Boundary for the LoadGame use case
 */
public class LoadGameInteractor implements LoadGameInputBoundary {

    private final LoadGameOutputBoundary presenter;
    private final GameDataAccessInterface gameRepository;

    /**
     * Constructor to initialize objects of LoadGameInteractor
     *
     * @param presenter      Output boundary for load game use case
     * @param gameRepository Data access interface for load game use case
     */
    public LoadGameInteractor(LoadGameOutputBoundary presenter, GameDataAccessInterface gameRepository) {
        this.presenter = presenter;
        this.gameRepository = gameRepository;
    }

    @Override
    public void execute(LoadGameInputData inputData) {
        Game gameToLoad = gameRepository.getGameByID(inputData.getGameID());
        Round lastRound = gameToLoad.getCurrentRound();

        LoadGameOutputData outputData = new LoadGameOutputData(gameToLoad.getID(),
                gameToLoad.getCurrentRound().getQuestion(),
                gameToLoad.getGenre(),
                gameToLoad.getDifficulty(),
                gameToLoad.getInitialLives(),
                gameToLoad.getCurrentLives(),
                gameToLoad.getMaxRounds(),
                gameToLoad.getRoundsPlayed(),
                lastRound.getMultipleChoiceAnswers(),
                gameToLoad.getScore());
        presenter.prepareView(outputData);
    }
}
