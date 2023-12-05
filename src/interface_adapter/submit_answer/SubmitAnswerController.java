package interface_adapter.submit_answer;

import use_case.submit_answer.SubmitAnswerInputBoundary;
import use_case.submit_answer.SubmitAnswerInputData;

/**
 * Controller for submit answer interface adapter
 */
public class SubmitAnswerController {
    private final SubmitAnswerInputBoundary submitAnswerInteractor;

    /**
     * Constructor to initialize objects of SubmitAnswerController
     *
     * @param submitAnswerInteractor submit answer input boundary
     */
    public SubmitAnswerController(SubmitAnswerInputBoundary submitAnswerInteractor) {
        this.submitAnswerInteractor = submitAnswerInteractor;
    }

    public void execute(String userAnswer, String gameId) {
        SubmitAnswerInputData inputData = new SubmitAnswerInputData(userAnswer, gameId);
        submitAnswerInteractor.execute(inputData);
    }
}
