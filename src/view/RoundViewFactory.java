package view;

import entity.*;
import interface_adapter.ViewManagerModel;
import interface_adapter.create_game.CreateGameViewModel;
import interface_adapter.finish_round.FinishRoundController;
import interface_adapter.finish_round.FinishRoundPresenter;
import interface_adapter.finish_round.FinishRoundViewModel;
import interface_adapter.game_over.GameOverViewModel;
import interface_adapter.load_game.LoadGameViewModel;
import interface_adapter.round.RoundViewModel;
import interface_adapter.submit_answer.SubmitAnswerController;
import interface_adapter.submit_answer.SubmitAnswerPresenter;
import interface_adapter.submit_answer.SubmitAnswerViewModel;
import interface_adapter.toggle_audio.ToggleAudioController;
import interface_adapter.toggle_audio.ToggleAudioPresenter;
import interface_adapter.toggle_audio.ToggleAudioViewModel;
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
                                   FinishRoundViewModel finishRoundViewModel,
                                   CreateGameViewModel createGameViewModel,
                                   LoadGameViewModel loadGameViewModel,
                                   ToggleAudioViewModel toggleAudioViewModel,
                                   GameOverViewModel gameOverViewModel,
                                   SubmitAnswerGameDataAccessInterface submitAnswerGameDataAccessInterface,
                                   RoundFactory roundFactory
    ) {


        FinishRoundGameDataAccessInterface finishRoundGameDataAccessInterface = (FinishRoundGameDataAccessInterface) submitAnswerGameDataAccessInterface;
        ToggleAudioGameDataAccessInterface toggleAudioDataAccessInterface = (ToggleAudioGameDataAccessInterface) submitAnswerGameDataAccessInterface;
        SubmitAnswerController submitAnswerController = createSubmitAnswerUseCase(submitAnswerViewModel, submitAnswerGameDataAccessInterface);
        FinishRoundController finishRoundController = createFinishRoundUseCase(viewManagerModel, gameOverViewModel, finishRoundViewModel, roundViewModel, finishRoundGameDataAccessInterface, roundFactory);
        ToggleAudioController toggleAudioController = createToggleAudioUseCase(toggleAudioViewModel, toggleAudioDataAccessInterface, roundViewModel);
        return new RoundView(viewManagerModel, roundViewModel, submitAnswerViewModel, submitAnswerController, finishRoundController, toggleAudioViewModel, toggleAudioController, finishRoundViewModel, createGameViewModel, loadGameViewModel);
    }

    private static SubmitAnswerController createSubmitAnswerUseCase(SubmitAnswerViewModel submitAnswerViewModel,
                                                                    SubmitAnswerGameDataAccessInterface gameDataAccessObject) {
        SubmitAnswerOutputBoundary submitAnswerPresenter = new SubmitAnswerPresenter(submitAnswerViewModel);
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
                                                                  FinishRoundViewModel finishRoundViewModel,
                                                                  RoundViewModel roundViewModel,
                                                                  FinishRoundGameDataAccessInterface gameDataAccessObject,
                                                                  RoundFactory roundFactory) {
        FinishRoundOutputBoundary finishRoundPresenter = new FinishRoundPresenter(viewManagerModel, gameOverViewModel, roundViewModel, finishRoundViewModel);
        FinishRoundInputBoundary finishRoundInteractor = new FinishRoundInteractor(finishRoundPresenter, gameDataAccessObject, roundFactory);
        return new FinishRoundController(finishRoundInteractor);

    }
}
