package use_case.submit_answer;

public interface SubmitAnswerInputBoundary {
    /**
     * Check the correctness of the user's answer and update/save the game accordingly. Report back the user's
     * correctness and what the correct answer is.
     *
     * @param inputData The input data object holding the game ID and user answer
     */
    void execute(SubmitAnswerInputData inputData);
}
