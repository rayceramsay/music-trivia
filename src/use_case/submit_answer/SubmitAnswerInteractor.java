package use_case.submit_answer;

import entity.Game;
import entity.Round;

public class SubmitAnswerInteractor implements SubmitAnswerInputBoundary {
    private final SubmitAnswerDataAccessInterface submitAnswerDataAccessObject;
    private final SubmitAnswerOutputBoundary submitAnswerPresenter;

    public SubmitAnswerInteractor(SubmitAnswerDataAccessInterface submitAnswerDataAccessObject,
                                  SubmitAnswerOutputBoundary submitAnswerPresenter) {
        this.submitAnswerDataAccessObject = submitAnswerDataAccessObject;
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
        Game game = submitAnswerDataAccessObject.getGameByID(gameId);
        Round currentRound = game.getCurrentRound();

        currentRound.setUserAnswer(userAnswer);

        boolean isUserAnswerCorrect = currentRound.isUserAnswerCorrect();
        String correctAnswer = currentRound.getCorrectAnswer();
        SubmitAnswerOutputData outputData = new SubmitAnswerOutputData(isUserAnswerCorrect, correctAnswer);

        if (isUserAnswerCorrect) {
            game.incrementScore();
            submitAnswerDataAccessObject.save(game);
            submitAnswerPresenter.prepareCorrectView(outputData);
        } else {
            game.decrementLives();
            submitAnswerDataAccessObject.save(game);
            submitAnswerPresenter.prepareIncorrectView(outputData);
        }
    }
}
