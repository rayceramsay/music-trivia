package interface_adapter.submit_answer;

import use_case.submit_answer.SubmitAnswerInputBoundary;
import use_case.submit_answer.SubmitAnswerInputData;

public class SubmitAnswerController {
    private final SubmitAnswerInputBoundary submitAnswerInteractor;

    public SubmitAnswerController(SubmitAnswerInputBoundary submitAnswerInteractor) {
        this.submitAnswerInteractor = submitAnswerInteractor;
    }

    public void execute(String userAnswer, String gameId) {
        SubmitAnswerInputData inputData = new SubmitAnswerInputData(userAnswer, gameId);
        submitAnswerInteractor.execute(inputData);
    }
}
