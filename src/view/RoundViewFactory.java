package view;

import data_access.api.SongAPI;
import data_access.api.SpotifyAPI;
import data_access.game_data.GameDataAccessInterface;
import entity.*;
import interface_adapter.ViewManagerModel;
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
import use_case.finish_round.FinishRoundInputBoundary;
import use_case.finish_round.FinishRoundInteractor;
import use_case.finish_round.FinishRoundOutputBoundary;
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
                                   GameDataAccessInterface gameDataAccessInterface,
                                   RoundFactory roundFactory
    ) {
        SubmitAnswerController submitAnswerController = createSubmitAnswerUseCase(submitAnswerViewModel, gameDataAccessInterface);
        FinishRoundController finishRoundController = createFinishRoundUseCase(viewManagerModel, gameOverViewModel, roundViewModel, gameDataAccessInterface, roundFactory);
        ToggleAudioController toggleAudioController = createToggleAudioUseCase(toggleAudioViewModel, gameDataAccessInterface, roundViewModel);
        return new RoundView(viewManagerModel, roundViewModel, submitAnswerViewModel, submitAnswerController, finishRoundController, toggleAudioViewModel, toggleAudioController);
    }

    private static SubmitAnswerController createSubmitAnswerUseCase(SubmitAnswerViewModel submitAnswerViewModel,
                                                                    GameDataAccessInterface gameDataAccessObject) {
        SubmitAnswerOutputBoundary submitAnswerPresenter = new SubmitAnswerPresenter(submitAnswerViewModel);
        SubmitAnswerInputBoundary submitAnswerInteractor = new SubmitAnswerInteractor(gameDataAccessObject, submitAnswerPresenter);
        return new SubmitAnswerController(submitAnswerInteractor);
    }

    private static ToggleAudioController createToggleAudioUseCase(ToggleAudioViewModel toggleAudioViewModel,
                                                                  GameDataAccessInterface gameDataAccessObject,
                                                                  RoundViewModel roundViewModel){
            ToggleAudioOutputBoundary toggleAudioPresenter = new ToggleAudioPresenter(toggleAudioViewModel, roundViewModel);
            ToggleAudioInputBoundary toggleAudioInteractor = new ToggleAudioInteractor(gameDataAccessObject, toggleAudioPresenter);
            return new ToggleAudioController(toggleAudioInteractor);
    }

    private static FinishRoundController createFinishRoundUseCase(ViewManagerModel viewManagerModel,
                                                                  GameOverViewModel gameOverViewModel,
                                                                  RoundViewModel roundViewModel,
                                                                  GameDataAccessInterface gameDataAccessObject,
                                                                  RoundFactory roundFactory) {
        FinishRoundOutputBoundary finishRoundPresenter = new FinishRoundPresenter(viewManagerModel, gameOverViewModel, roundViewModel);
        FinishRoundInputBoundary finishRoundInteractor = new FinishRoundInteractor(finishRoundPresenter, gameDataAccessObject, roundFactory);
        return new FinishRoundController(finishRoundInteractor);

    }
}
