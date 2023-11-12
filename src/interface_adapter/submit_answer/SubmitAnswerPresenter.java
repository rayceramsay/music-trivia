package interface_adapter.submit_answer;

import use_case.submit_answer.SubmitAnswerOutputBoundary;
import use_case.submit_answer.SubmitAnswerOutputData;

public class SubmitAnswerPresenter implements SubmitAnswerOutputBoundary {
    private final SubmitAnswerViewModel submitAnswerViewModel;

    public SubmitAnswerPresenter(SubmitAnswerViewModel submitAnswerViewModel) {
        this.submitAnswerViewModel = submitAnswerViewModel;
    }

    @Override
    public void prepareView(SubmitAnswerOutputData outputData) {
        SubmitAnswerState submitAnswerState = submitAnswerViewModel.getState();

        if (outputData.isUserAnswerCorrect()) {
            submitAnswerState.setCorrectnessTitle("Correct!");
            submitAnswerState.setCorrectnessMessage("Your answer is correct!");
        } else {
            submitAnswerState.setCorrectnessTitle("Incorrect!");
            String correctnessMessage = String.format("Your answer is incorrect!\nThe correct answer is \"%s\"",
                    outputData.getCorrectAnswer());
            submitAnswerState.setCorrectnessMessage(correctnessMessage);
        }

        submitAnswerViewModel.firePropertyChanged();
    }
}
