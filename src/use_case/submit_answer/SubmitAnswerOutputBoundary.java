package use_case.submit_answer;

public interface SubmitAnswerOutputBoundary {
    void prepareCorrectView(SubmitAnswerOutputData outputData);
    void prepareIncorrectView(SubmitAnswerOutputData outputData);
}
