package view;

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
import use_case.finish_round.FinishRoundGameDataAccessInterface;
import use_case.finish_round.FinishRoundInputBoundary;
import use_case.finish_round.FinishRoundInteractor;
import use_case.finish_round.FinishRoundOutputBoundary;
import use_case.submit_answer.SubmitAnswerGameDataAccessInterface;
import use_case.submit_answer.SubmitAnswerInputBoundary;
import use_case.submit_answer.SubmitAnswerInteractor;
import use_case.submit_answer.SubmitAnswerOutputBoundary;
import use_case.toggle_audio.ToggleAudioGameDataAccessInterface;
import use_case.toggle_audio.ToggleAudioInputBoundary;
import use_case.toggle_audio.ToggleAudioInteractor;
import use_case.toggle_audio.ToggleAudioOutputBoundary;

public class RoundViewFactory {

    private RoundViewFactory() {
    }

    /**
     * Creates an instance of RoundView
     *
     * @param viewManagerModel                    View manager model
     * @param roundViewModel                      View model for Round
     * @param submitAnswerViewModel               View model for SubmitAnswer
     * @param toggleAudioViewModel                View model for ToggleAudio
     * @param gameOverViewModel                   View model for GameOver
     * @param submitAnswerGameDataAccessInterface Data access interface for SubmitAnswer
     * @return RoundView
     */
    public static RoundView create(ViewManagerModel viewManagerModel,
                                   RoundViewModel roundViewModel,
                                   SubmitAnswerViewModel submitAnswerViewModel,
                                   ToggleAudioViewModel toggleAudioViewModel,
                                   GameOverViewModel gameOverViewModel,
                                   SubmitAnswerGameDataAccessInterface submitAnswerGameDataAccessInterface) {

        FinishRoundGameDataAccessInterface finishRoundGameDataAccessInterface = (FinishRoundGameDataAccessInterface) submitAnswerGameDataAccessInterface;
        ToggleAudioGameDataAccessInterface toggleAudioDataAccessInterface = (ToggleAudioGameDataAccessInterface) submitAnswerGameDataAccessInterface;
        SubmitAnswerController submitAnswerController = createSubmitAnswerUseCase(submitAnswerViewModel, submitAnswerGameDataAccessInterface);
        ToggleAudioController toggleAudioController = createToggleAudioUseCase(toggleAudioViewModel, toggleAudioDataAccessInterface, roundViewModel);
        FinishRoundController finishRoundController = createFinishRoundUseCase(viewManagerModel, gameOverViewModel, roundViewModel, finishRoundGameDataAccessInterface);

        return new RoundView(viewManagerModel, roundViewModel, submitAnswerViewModel, submitAnswerController, finishRoundController, toggleAudioViewModel, toggleAudioController);
    }

    /**
     * Creates an instance of Submit AnswerController
     *
     * @param submitAnswerViewModel View model for SubmitAnswer
     * @param gameDataAccessObject  Data access object
     * @return SubmitAnswerController
     */
    private static SubmitAnswerController createSubmitAnswerUseCase(SubmitAnswerViewModel submitAnswerViewModel,
                                                                    SubmitAnswerGameDataAccessInterface gameDataAccessObject) {
        SubmitAnswerOutputBoundary submitAnswerPresenter = new SubmitAnswerPresenter(submitAnswerViewModel);
        SubmitAnswerInputBoundary submitAnswerInteractor = new SubmitAnswerInteractor(gameDataAccessObject, submitAnswerPresenter);

        return new SubmitAnswerController(submitAnswerInteractor);
    }

    /**
     * Creates an instance of ToggleAudioController
     *
     * @param toggleAudioViewModel View model for ToggleAudio
     * @param gameDataAccessObject DataAccessObject
     * @param roundViewModel       View model for Round
     * @return ToggleAudioController
     */
    private static ToggleAudioController createToggleAudioUseCase(ToggleAudioViewModel toggleAudioViewModel,
                                                                  ToggleAudioGameDataAccessInterface gameDataAccessObject,
                                                                  RoundViewModel roundViewModel) {
        ToggleAudioOutputBoundary toggleAudioPresenter = new ToggleAudioPresenter(toggleAudioViewModel, roundViewModel);
        ToggleAudioInputBoundary toggleAudioInteractor = new ToggleAudioInteractor(gameDataAccessObject, toggleAudioPresenter);

        return new ToggleAudioController(toggleAudioInteractor);
    }

    /**
     * Creates an instance of FinishRoundController
     *
     * @param viewManagerModel     View manager model
     * @param gameOverViewModel    View model for GameOver
     * @param roundViewModel       View model for Round
     * @param gameDataAccessObject Data access object
     * @return FinishRoundController
     */
    private static FinishRoundController createFinishRoundUseCase(ViewManagerModel viewManagerModel,
                                                                  GameOverViewModel gameOverViewModel,
                                                                  RoundViewModel roundViewModel,
                                                                  FinishRoundGameDataAccessInterface gameDataAccessObject) {
        FinishRoundOutputBoundary finishRoundPresenter = new FinishRoundPresenter(viewManagerModel, gameOverViewModel, roundViewModel);
        FinishRoundInputBoundary finishRoundInteractor = new FinishRoundInteractor(finishRoundPresenter, gameDataAccessObject);

        return new FinishRoundController(finishRoundInteractor);

    }
}
