package use_case.submit_answer;

import data_access.game_data.GameDataAccessInterface;
import entity.Game;
import entity.Round;

public class SubmitAnswerInteractor implements SubmitAnswerInputBoundary {
    private final GameDataAccessInterface gameDataAccessObject;
    private final SubmitAnswerOutputBoundary submitAnswerPresenter;

    /**
     * Constructor to initialize objects of SubmitAnswerInteractor
     *
     * @param gameDataAccessObject  Data access interface for submit answer use case
     * @param submitAnswerPresenter Output boundary for submit answer use case
     */
    public SubmitAnswerInteractor(GameDataAccessInterface gameDataAccessObject,
                                  SubmitAnswerOutputBoundary submitAnswerPresenter) {
        this.gameDataAccessObject = gameDataAccessObject;
        this.submitAnswerPresenter = submitAnswerPresenter;
    }

    @Override
    public void execute(SubmitAnswerInputData inputData) {
        String userAnswer = inputData.getUserAnswer();
        String gameId = inputData.getGameId();
        Game game = gameDataAccessObject.getGameByID(gameId);
        Round currentRound = game.getCurrentRound();

        currentRound.getSong().getAudio().stop();
        currentRound.setUserAnswer(userAnswer);

        boolean isUserAnswerCorrect = currentRound.isUserAnswerCorrect();
        String correctAnswer = currentRound.getCorrectAnswer();
        SubmitAnswerOutputData outputData = new SubmitAnswerOutputData(isUserAnswerCorrect, correctAnswer);

        if (isUserAnswerCorrect) {
            game.incrementScore();
        } else {
            game.decrementLives();
        }

        gameDataAccessObject.save(game);
        submitAnswerPresenter.prepareView(outputData);
    }
}
