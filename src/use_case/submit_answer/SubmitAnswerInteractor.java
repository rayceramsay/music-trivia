package use_case.submit_answer;

public class SubmitAnswerInteractor implements SubmitAnswerInputBoundary {
    private final SubmitAnswerDataAccessInterface submitAnswerDataAccessObject;
    private final SubmitAnswerOutputBoundary submitAnswerPresenter;

    public SubmitAnswerInteractor(SubmitAnswerDataAccessInterface submitAnswerDataAccessObject,
                                  SubmitAnswerOutputBoundary submitAnswerPresenter) {
        this.submitAnswerDataAccessObject = submitAnswerDataAccessObject;
        this.submitAnswerPresenter = submitAnswerPresenter;
    }

    @Override
    public void execute(SubmitAnswerInputData inputData) {
        // TODO implement submit answer use case logic (i.e. prepare the game for its next steps and then inform the
        //  user about the results from the current round)
        //   - Check if answer is correct -> handle game score and lives accordingly
        //   - Check if game is over -> either mark the game as finished or create the next round to be played
        //   - Save game to persistence layer
        //   - Send data to presenter indicating whether the user's answer was correct, what the correct answer was,
        //   and whether the game is over
    }
}
