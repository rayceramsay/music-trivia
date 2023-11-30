package view;

import data_access.api.SongAPI;
import data_access.api.SpotifyAPI;
import entity.*;
import interface_adapter.ViewManagerModel;
import interface_adapter.exit_round.ExitRoundController;
import interface_adapter.exit_round.ExitRoundPresenter;
import interface_adapter.exit_round.ExitRoundViewModel;
import interface_adapter.finish_round.FinishRoundController;
import interface_adapter.finish_round.FinishRoundPresenter;
import interface_adapter.game_over.GameOverViewModel;
import interface_adapter.round.RoundViewModel;
import interface_adapter.submit_answer.SubmitAnswerController;
import interface_adapter.submit_answer.SubmitAnswerPresenter;
import interface_adapter.submit_answer.SubmitAnswerViewModel;
import interface_adapter.toggle_audio.ToggleAudioController;
import interface_adapter.toggle_audio.ToggleAudioPresenter;
import interface_adapter.toggle_audio.ToggleAudioViewModel;
import use_case.exit_round.ExitRoundGameDataAccessInterface;
import use_case.exit_round.ExitRoundInputBoundary;
import use_case.exit_round.ExitRoundInteractor;
import use_case.exit_round.ExitRoundOutputBoundary;
import use_case.finish_round.FinishRoundGameDataAccessInterface;
import use_case.finish_round.FinishRoundInputBoundary;
import use_case.finish_round.FinishRoundInteractor;
import use_case.finish_round.FinishRoundOutputBoundary;
import use_case.submit_answer.SubmitAnswerGameDataAccessInterface;
import use_case.submit_answer.SubmitAnswerInputBoundary;
import use_case.submit_answer.SubmitAnswerInteractor;
import use_case.submit_answer.SubmitAnswerOutputBoundary;
import use_case.toggle_audio.*;

public class RoundViewFactory {

    private RoundViewFactory() {}

    public static RoundView create(ViewManagerModel viewManagerModel,
                                   RoundViewModel roundViewModel,
                                   SubmitAnswerViewModel submitAnswerViewModel,
                                   ToggleAudioViewModel toggleAudioViewModel,
                                   GameOverViewModel gameOverViewModel,
                                   ExitRoundViewModel exitRoundViewModel,
                                   SubmitAnswerGameDataAccessInterface submitAnswerGameDataAccessInterface,
                                   RoundFactory roundFactory
    ) {

        FinishRoundGameDataAccessInterface finishRoundGameDataAccessInterface = (FinishRoundGameDataAccessInterface) submitAnswerGameDataAccessInterface;
        ToggleAudioGameDataAccessInterface toggleAudioDataAccessInterface = (ToggleAudioGameDataAccessInterface) submitAnswerGameDataAccessInterface;
        ExitRoundGameDataAccessInterface exitRoundGameDataAccessInterface = (ExitRoundGameDataAccessInterface) submitAnswerGameDataAccessInterface;

        SubmitAnswerController submitAnswerController = createSubmitAnswerUseCase(submitAnswerViewModel, toggleAudioViewModel, submitAnswerGameDataAccessInterface);
        FinishRoundController finishRoundController = createFinishRoundUseCase(viewManagerModel, gameOverViewModel, roundViewModel, finishRoundGameDataAccessInterface, roundFactory);
        ToggleAudioController toggleAudioController = createToggleAudioUseCase(toggleAudioViewModel, toggleAudioDataAccessInterface, roundViewModel);
        ExitRoundController exitRoundController = createExitRoundUseCase(viewManagerModel, exitRoundViewModel, roundViewModel, toggleAudioViewModel, exitRoundGameDataAccessInterface);

        return new RoundView(viewManagerModel, roundViewModel, submitAnswerViewModel, toggleAudioViewModel, submitAnswerController, toggleAudioController, finishRoundController, exitRoundController);
    }

    private static SubmitAnswerController createSubmitAnswerUseCase(SubmitAnswerViewModel submitAnswerViewModel,
                                                                    ToggleAudioViewModel toggleAudioViewModel,
                                                                    SubmitAnswerGameDataAccessInterface gameDataAccessObject) {
        SubmitAnswerOutputBoundary submitAnswerPresenter = new SubmitAnswerPresenter(submitAnswerViewModel, toggleAudioViewModel);
        SubmitAnswerInputBoundary submitAnswerInteractor = new SubmitAnswerInteractor(gameDataAccessObject, submitAnswerPresenter);

        return new SubmitAnswerController(submitAnswerInteractor);
    }

    private static ToggleAudioController createToggleAudioUseCase(ToggleAudioViewModel toggleAudioViewModel,
                                                                  ToggleAudioGameDataAccessInterface gameDataAccessObject,
                                                                  RoundViewModel roundViewModel){
            ToggleAudioOutputBoundary toggleAudioPresenter = new ToggleAudioPresenter(toggleAudioViewModel, roundViewModel);
            ToggleAudioInputBoundary toggleAudioInteractor = new ToggleAudioInteractor(gameDataAccessObject, toggleAudioPresenter);

            return new ToggleAudioController(toggleAudioInteractor);
    }

    private static FinishRoundController createFinishRoundUseCase(ViewManagerModel viewManagerModel,
                                                                  GameOverViewModel gameOverViewModel,
                                                                  RoundViewModel roundViewModel,
                                                                  FinishRoundGameDataAccessInterface gameDataAccessObject,
                                                                  RoundFactory roundFactory) {
        FinishRoundOutputBoundary finishRoundPresenter = new FinishRoundPresenter(viewManagerModel, gameOverViewModel, roundViewModel);
        FinishRoundInputBoundary finishRoundInteractor = new FinishRoundInteractor(finishRoundPresenter, gameDataAccessObject, roundFactory);

        return new FinishRoundController(finishRoundInteractor);
    }

    private static ExitRoundController createExitRoundUseCase(ViewManagerModel viewManagerModel,
                                                              ExitRoundViewModel exitRoundViewModel,
                                                              RoundViewModel roundViewModel,
                                                              ToggleAudioViewModel toggleAudioViewModel,
                                                              ExitRoundGameDataAccessInterface gameDataAccessObject) {
        ExitRoundOutputBoundary exitRoundPresenter = new ExitRoundPresenter(viewManagerModel, exitRoundViewModel, roundViewModel, toggleAudioViewModel);
        ExitRoundInputBoundary exitRoundInteractor = new ExitRoundInteractor(exitRoundPresenter, gameDataAccessObject);

        return new ExitRoundController(exitRoundInteractor);
    }
}
