package use_case.submit_answer;

import data_access.game_data.GameDataAccessInterface;
import entity.Game;
import entity.Round;

public class SubmitAnswerInteractor implements SubmitAnswerInputBoundary {
    private final GameDataAccessInterface gameDataAccessObject;
    private final SubmitAnswerOutputBoundary submitAnswerPresenter;

    public SubmitAnswerInteractor(GameDataAccessInterface gameDataAccessObject,
                                  SubmitAnswerOutputBoundary submitAnswerPresenter) {
        this.gameDataAccessObject = gameDataAccessObject;
        this.submitAnswerPresenter = submitAnswerPresenter;
    }

    /**
     * Check the correctness of the user's answer and update/save the game accordingly. Report back the user's
     * correctness and what the correct answer is.
     *
     * @param inputData The input data object holding the game ID and user answer
     */
    @Override
    public void execute(SubmitAnswerInputData inputData) {
        String userAnswer = inputData.getUserAnswer();
        String gameId = inputData.getGameId();
        Game game = gameDataAccessObject.getGameByID(gameId);
        Round currentRound = game.getCurrentRound();

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
