package view;

import interface_adapter.round.RoundViewModel;
import interface_adapter.submit_answer.SubmitAnswerController;
import interface_adapter.submit_answer.SubmitAnswerPresenter;
import interface_adapter.submit_answer.SubmitAnswerViewModel;
import use_case.submit_answer.SubmitAnswerGameDataAccessInterface;
import use_case.submit_answer.SubmitAnswerInputBoundary;
import use_case.submit_answer.SubmitAnswerInteractor;
import use_case.submit_answer.SubmitAnswerOutputBoundary;

public class RoundViewFactory {
    private RoundViewFactory() {}

    public static RoundView create(RoundViewModel roundViewModel, SubmitAnswerViewModel submitAnswerViewModel,
                                   SubmitAnswerGameDataAccessInterface gameDataAccessObject) {
        SubmitAnswerController submitAnswerController = createSubmitAnswerUseCase(submitAnswerViewModel, gameDataAccessObject);

        return new RoundView(roundViewModel, submitAnswerViewModel, submitAnswerController);

    }

    private static SubmitAnswerController createSubmitAnswerUseCase(SubmitAnswerViewModel submitAnswerViewModel,
                                                                    SubmitAnswerGameDataAccessInterface gameDataAccessObject) {
        SubmitAnswerOutputBoundary submitAnswerPresenter = new SubmitAnswerPresenter(submitAnswerViewModel);
        SubmitAnswerInputBoundary submitAnswerInteractor = new SubmitAnswerInteractor(gameDataAccessObject, submitAnswerPresenter);

        return new SubmitAnswerController(submitAnswerInteractor);
    }
}
