package view;

import interface_adapter.round.RoundViewModel;
import interface_adapter.submit_answer.SubmitAnswerController;
import interface_adapter.submit_answer.SubmitAnswerPresenter;
import interface_adapter.submit_answer.SubmitAnswerViewModel;
import interface_adapter.toggle_audio.ToggleAudioController;
import interface_adapter.toggle_audio.ToggleAudioPresenter;
import interface_adapter.toggle_audio.ToggleAudioViewModel;
import use_case.submit_answer.SubmitAnswerGameDataAccessInterface;
import use_case.submit_answer.SubmitAnswerInputBoundary;
import use_case.submit_answer.SubmitAnswerInteractor;
import use_case.submit_answer.SubmitAnswerOutputBoundary;
import use_case.toggle_audio.*;

public class RoundViewFactory {
    private RoundViewFactory() {}

    public static RoundView create(RoundViewModel roundViewModel,
                                   SubmitAnswerViewModel submitAnswerViewModel,
                                   ToggleAudioViewModel toggleAudioViewModel,
                                   SubmitAnswerGameDataAccessInterface submitAnswerGameDataAccessInterface) {

        ToggleAudioGameDataAccessInterface toggleAudioDataAccessInterface = (ToggleAudioGameDataAccessInterface) submitAnswerGameDataAccessInterface;
        SubmitAnswerController submitAnswerController = createSubmitAnswerUseCase(submitAnswerViewModel, submitAnswerGameDataAccessInterface);
        ToggleAudioController toggleAudioController = createToggleAudioUseCase(toggleAudioViewModel, toggleAudioDataAccessInterface, roundViewModel);

        return new RoundView(roundViewModel, submitAnswerViewModel, submitAnswerController, toggleAudioViewModel, toggleAudioController);

    }

    private static SubmitAnswerController createSubmitAnswerUseCase(SubmitAnswerViewModel submitAnswerViewModel,
                                                                    SubmitAnswerGameDataAccessInterface gameDataAccessObject) {
        SubmitAnswerOutputBoundary submitAnswerPresenter = new SubmitAnswerPresenter(submitAnswerViewModel);
        SubmitAnswerInputBoundary submitAnswerInteractor = new SubmitAnswerInteractor(gameDataAccessObject, submitAnswerPresenter);

        return new SubmitAnswerController(submitAnswerInteractor);
    }
    private static ToggleAudioController createToggleAudioUseCase(ToggleAudioViewModel toggleAudioViewModel,
                                                                  ToggleAudioGameDataAccessInterface gameDataAccessObject,
                                                                  RoundViewModel roundViewModel) {
        ToggleAudioOutputBoundary toggleAudioPresenter = new ToggleAudioPresenter(toggleAudioViewModel, roundViewModel);
        ToggleAudioInputBoundary toggleAudioInteractor = new ToggleAudioInteractor(gameDataAccessObject, toggleAudioPresenter);

        return new ToggleAudioController(toggleAudioInteractor);
    }
}
