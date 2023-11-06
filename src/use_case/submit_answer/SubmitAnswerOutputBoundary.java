package use_case.submit_answer;

public interface SubmitAnswerOutputBoundary {
    public void prepareCorrectView(SubmitAnswerOutputData outputData);
    public void prepareIncorrectView(SubmitAnswerOutputData outputData);
}
