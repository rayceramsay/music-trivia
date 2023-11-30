package use_case.submit_answer;

import data_access.InMemoryGameDataAccessObject;
import entity.*;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class SubmitAnswerInteractorTest {

    private final String CORRECT_ANSWER = "correct";
    private final String INCORRECT_ANSWER = "incorrect";
    private final int INITIAL_LIVES = 10;
    private final int INITIAL_SCORE = 0;
    private SubmitAnswerGameDataAccessInterface gameDataAccessObject;
    private Game game;

    /**
     * Initialize each test to have an existing game with the current round already set and a data access object.
     */
    @Before
    public void init() {
        game = new CommonGame("pop", "hard", 15, INITIAL_LIVES);
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
}
