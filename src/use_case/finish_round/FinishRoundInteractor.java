package use_case.finish_round;

import entity.CommonRoundFactory;
import entity.Game;
import entity.Round;
import entity.RoundFactory;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Interactor which implements the Input Boundary for the FinishRound use case
 */
public class FinishRoundInteractor implements FinishRoundInputBoundary {
    private final FinishRoundGameDataAccessInterface gameDataAccessObject;
    private final FinishRoundOutputBoundary finishRoundPresenter;
    private final RoundFactory roundFactory;

    /**
     * Constructor to initialize objects of FinishRoundInteractor
     *
     * @param finishRoundPresenter Output boundary for finish round use case
     * @param gameDataAccessObject Data access interface for finish round use case
     */
    public FinishRoundInteractor(FinishRoundOutputBoundary finishRoundPresenter, FinishRoundGameDataAccessInterface gameDataAccessObject) {
        this.gameDataAccessObject = gameDataAccessObject;
        this.finishRoundPresenter = finishRoundPresenter;
        this.roundFactory = new CommonRoundFactory();
    }

    @Override
    public void execute(FinishRoundInputData inputData) {
        String gameId = inputData.getGameId();
        Game game = gameDataAccessObject.getGameByID(gameId);

        if (game.isGameOver()) {
            game.setFinishedAt(LocalDateTime.now());
            gameDataAccessObject.save(game);

            FinishRoundOutputData outputData = new FinishRoundOutputData();
            outputData.setScore(game.getScore());

            finishRoundPresenter.prepareGameOverView(outputData);
        } else {
            String gameGenre = game.getGenre();
            String gameDifficulty = game.getDifficulty().toLowerCase().trim();
            Round nextRound;

            if (Objects.equals(gameDifficulty, "hard")) {
                nextRound = roundFactory.createHardRound(gameGenre);
            } else if (Objects.equals(gameDifficulty, "medium")) {
                nextRound = roundFactory.createMediumRound(gameGenre);
            } else {
                nextRound = roundFactory.createEasyRound(gameGenre);
            }
            game.setCurrentRound(nextRound);
            gameDataAccessObject.save(game);

            FinishRoundOutputData outputData = new FinishRoundOutputData();
            outputData.setGenre(gameGenre);
            outputData.setLives(game.getCurrentLives());
            outputData.setRoundNumber(game.getRoundsPlayed());

            finishRoundPresenter.prepareNextRoundView(outputData);
        }
    }
}
