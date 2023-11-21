package view;

import interface_adapter.ViewManagerModel;
import interface_adapter.finish_round.FinishRoundController;
import interface_adapter.finish_round.FinishRoundPresenter;
import interface_adapter.game_over.GameOverViewModel;
import interface_adapter.round.RoundViewModel;
import interface_adapter.submit_answer.SubmitAnswerController;
import interface_adapter.submit_answer.SubmitAnswerPresenter;
import interface_adapter.submit_answer.SubmitAnswerViewModel;
import use_case.finish_round.FinishRoundGameDataAccessInterface;
import use_case.finish_round.FinishRoundInputBoundary;
import use_case.finish_round.FinishRoundInteractor;
import use_case.finish_round.FinishRoundOutputBoundary;
import use_case.submit_answer.SubmitAnswerGameDataAccessInterface;
import use_case.submit_answer.SubmitAnswerInputBoundary;
import use_case.submit_answer.SubmitAnswerInteractor;
import use_case.submit_answer.SubmitAnswerOutputBoundary;

public class RoundViewFactory {
    private RoundViewFactory() {}

    public static RoundView create(ViewManagerModel viewManagerModel,
                                   RoundViewModel roundViewModel,
                                   SubmitAnswerViewModel submitAnswerViewModel,
                                   GameOverViewModel gameOverViewModel,
                                   SubmitAnswerGameDataAccessInterface submitAnswerGameDataAccessInterface
    ) {

        // submitAnswerGameDataAccessInterface and FinishRoundGameDataAccessInterface are the same so cast to reduce repeated parameters passed to function
        FinishRoundGameDataAccessInterface finishRoundGameDataAccessInterface = (FinishRoundGameDataAccessInterface) submitAnswerGameDataAccessInterface;

        SubmitAnswerController submitAnswerController = createSubmitAnswerUseCase(submitAnswerViewModel, submitAnswerGameDataAccessInterface);
        FinishRoundController finishRoundController = createFinishRoundUseCase(viewManagerModel, gameOverViewModel, roundViewModel, finishRoundGameDataAccessInterface);

        return new RoundView(viewManagerModel, roundViewModel, submitAnswerViewModel, submitAnswerController, finishRoundController);

    }

    private static SubmitAnswerController createSubmitAnswerUseCase(SubmitAnswerViewModel submitAnswerViewModel,
                                                                    SubmitAnswerGameDataAccessInterface gameDataAccessObject) {
        SubmitAnswerOutputBoundary submitAnswerPresenter = new SubmitAnswerPresenter(submitAnswerViewModel);
        SubmitAnswerInputBoundary submitAnswerInteractor = new SubmitAnswerInteractor(gameDataAccessObject, submitAnswerPresenter);

        return new SubmitAnswerController(submitAnswerInteractor);
    }
    private static FinishRoundController createFinishRoundUseCase(ViewManagerModel viewManagerModel,
                                                                  GameOverViewModel gameOverViewModel,
                                                                  RoundViewModel roundViewModel,
                                                                  FinishRoundGameDataAccessInterface gameDataAccessObject) {
        FinishRoundOutputBoundary finishRoundPresenter = new FinishRoundPresenter(viewManagerModel, gameOverViewModel, roundViewModel);
        FinishRoundInputBoundary finishRoundInteractor = new FinishRoundInteractor(finishRoundPresenter, gameDataAccessObject);

        return new FinishRoundController(finishRoundInteractor);
    }
}
