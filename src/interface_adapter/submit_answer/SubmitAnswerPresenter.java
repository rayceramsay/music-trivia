package interface_adapter.submit_answer;

import interface_adapter.toggle_audio.ToggleAudioState;
import interface_adapter.toggle_audio.ToggleAudioViewModel;
import use_case.submit_answer.SubmitAnswerOutputBoundary;
import use_case.submit_answer.SubmitAnswerOutputData;

public class SubmitAnswerPresenter implements SubmitAnswerOutputBoundary {

    private final SubmitAnswerViewModel submitAnswerViewModel;
    private final ToggleAudioViewModel toggleAudioViewModel;

    public SubmitAnswerPresenter(SubmitAnswerViewModel submitAnswerViewModel, ToggleAudioViewModel toggleAudioViewModel) {
        this.submitAnswerViewModel = submitAnswerViewModel;
        this.toggleAudioViewModel = toggleAudioViewModel;
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

        ToggleAudioState toggleAudioState = toggleAudioViewModel.getState();
        toggleAudioState.setImgPath(toggleAudioViewModel.getPlayButtonImagePath());

        submitAnswerViewModel.firePropertyChanged();
        toggleAudioViewModel.firePropertyChanged();
    }
}
