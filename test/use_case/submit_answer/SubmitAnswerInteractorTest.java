package use_case.submit_answer;

import data_access.game_data.GameDataAccessInterface;
import data_access.game_data.InMemoryGameDataAccessObject;
import entity.*;
import interface_adapter.ViewManagerModel;
import interface_adapter.create_game.CreateGamePresenter;
import interface_adapter.create_game.CreateGameViewModel;
import interface_adapter.round.RoundState;
import interface_adapter.round.RoundViewModel;
import interface_adapter.submit_answer.SubmitAnswerPresenter;
import interface_adapter.submit_answer.SubmitAnswerState;
import interface_adapter.submit_answer.SubmitAnswerViewModel;
import interface_adapter.toggle_audio.ToggleAudioState;
import interface_adapter.toggle_audio.ToggleAudioViewModel;
import org.junit.Before;
import org.junit.Test;
import use_case.create_game.CreateGameInputBoundary;
import use_case.create_game.CreateGameInputData;
import use_case.create_game.CreateGameInteractor;
import view.RoundView;

import static org.junit.Assert.*;

public class SubmitAnswerInteractorTest {

    private final String CORRECT_ANSWER = "correct";
    private final String INCORRECT_ANSWER = "incorrect";
    private final int INITIAL_LIVES = 10;
    private final int INITIAL_SCORE = 0;
    private GameDataAccessInterface gameDataAccessObject;
    private Game game;

    /**
     * Initialize each test to have an existing game with the current round already set and a data access object.
     */
    @Before
    public void init() {
        game = new CommonGame("pop", "easy", 15, INITIAL_LIVES);
        Song song = new CommonSong(CORRECT_ANSWER, "me", new TestPlayableAudio("song.mp3"));
        Round round = new TextInputRound(song, "What song is this?", CORRECT_ANSWER);
        game.setCurrentRound(round);

        gameDataAccessObject = new InMemoryGameDataAccessObject();
        gameDataAccessObject.save(game);
    }

    /**
     * Test that a correct user answer increments the game score, maintains the user's lives, and confirms that the user
     * guessed correctly.
     */
    @Test
    public void correctAnswerTest() {
        SubmitAnswerInputData inputData = new SubmitAnswerInputData(CORRECT_ANSWER, game.getID());

        SubmitAnswerOutputBoundary correctPresenter = new SubmitAnswerOutputBoundary() {
            @Override
            public void prepareView(SubmitAnswerOutputData outputData) {
                assertEquals(INITIAL_SCORE + 1, game.getScore());
                assertEquals(INITIAL_LIVES, game.getCurrentLives());
                assertEquals(CORRECT_ANSWER, outputData.getCorrectAnswer());
                assertTrue(outputData.isUserAnswerCorrect());
                assertEquals(CORRECT_ANSWER, game.getCurrentRound().getUserAnswer());
            }
        };

        SubmitAnswerInputBoundary interactor = new SubmitAnswerInteractor(gameDataAccessObject, correctPresenter);
        interactor.execute(inputData);
    }


    /**
     * Test that an incorrect user answer maintains the game score, decrements the user's lives, and confirms that the
     * user guessed incorrectly.
     */
    @Test
    public void incorrectAnswerTest() {
        SubmitAnswerInputData inputData = new SubmitAnswerInputData(INCORRECT_ANSWER, game.getID());

        SubmitAnswerOutputBoundary correctPresenter = new SubmitAnswerOutputBoundary() {
            @Override
            public void prepareView(SubmitAnswerOutputData outputData) {
                assertEquals(INITIAL_SCORE, game.getScore());
                assertEquals(INITIAL_LIVES - 1, game.getCurrentLives());
                assertEquals(CORRECT_ANSWER, outputData.getCorrectAnswer());
                assertFalse(outputData.isUserAnswerCorrect());
                assertEquals(INCORRECT_ANSWER, game.getCurrentRound().getUserAnswer());
            }
        };

        SubmitAnswerInputBoundary interactor = new SubmitAnswerInteractor(gameDataAccessObject, correctPresenter);
        interactor.execute(inputData);
    }
    @Test
    public void furtherTesting () {
        SubmitAnswerInputData inputData = new SubmitAnswerInputData(CORRECT_ANSWER, game.getID());
        SubmitAnswerViewModel submitAnswerViewModel = new SubmitAnswerViewModel(RoundView.VIEW_NAME);
        ToggleAudioViewModel toggleAudioViewModel = new ToggleAudioViewModel(ToggleAudioViewModel.STATE_PROPERTY);

        SubmitAnswerOutputBoundary submitAnswerPresenter = new SubmitAnswerPresenter(submitAnswerViewModel, toggleAudioViewModel);
        SubmitAnswerInputBoundary interactor = new SubmitAnswerInteractor(gameDataAccessObject, submitAnswerPresenter);

        interactor.execute(inputData);

        SubmitAnswerState answerState = submitAnswerViewModel.getState();
        ToggleAudioState toggleState = toggleAudioViewModel.getState();
        assert answerState.getCorrectnessMessage().contains("Your answer is correct!");
        assert answerState.getCorrectnessTitle() == "Correct!";


        assert toggleState.getImgPath() == toggleAudioViewModel.getPlayButtonImagePath();

        interactor.execute(new SubmitAnswerInputData("wrong", game.getID()));
        assert answerState.getCorrectnessMessage().contains("Your answer is incorrect!");
        assert answerState.getCorrectnessTitle() == "Incorrect!";

    }
}
