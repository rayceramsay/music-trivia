package use_case.submit_answer;

/**
 * Output boundary for submit answer use case
 */
public interface SubmitAnswerOutputBoundary {
    /**
     * If the user answer is correct, set correct title and message in state.
     * If user answer is incorrect, set incorrect title and message and show correct answer in state.
     *
     * @param outputData if user answer is correct, correct answer of round
     */
    void prepareView(SubmitAnswerOutputData outputData);
}
