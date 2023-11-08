package use_case.submit_answer;

import data_access.InMemoryGameDataAccessObject;
import entity.*;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class SubmitAnswerInteractorTest {
    private final String CORRECT_ANSWER = "correct";
    private final String INCORRECT_ANSWER = "incorrect";
    private SubmitAnswerDataAccessInterface gameDao;
    private Game game;

    @Before
    public void init() {
        game = new CommonGame("pop", "hard", 15, 10);
        Song song = new CommonSong(CORRECT_ANSWER, "me", new FileMP3PlayableAudio("path/song.mp3"));
        Round round = new TextInputRound(song, "What song is this?", CORRECT_ANSWER);
        game.setCurrentRound(round);

        gameDao = new InMemoryGameDataAccessObject();
        gameDao.save(game);
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
            public void prepareCorrectView(SubmitAnswerOutputData outputData) {
                assertEquals(1, game.getScore());
                assertEquals(10, game.getCurrentLives());
                assertEquals(CORRECT_ANSWER, outputData.getCorrectAnswer());
                assertTrue(outputData.isUserAnswerCorrect());
                assertEquals(CORRECT_ANSWER, game.getCurrentRound().getUserAnswer());
            }

            @Override
            public void prepareIncorrectView(SubmitAnswerOutputData outputData) {
                fail("Use case incorrect is unexpected.");
            }
        };

        SubmitAnswerInputBoundary interactor = new SubmitAnswerInteractor(gameDao, correctPresenter);
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
            public void prepareCorrectView(SubmitAnswerOutputData outputData) {
                fail("Use case correct is unexpected.");
            }

            @Override
            public void prepareIncorrectView(SubmitAnswerOutputData outputData) {
                assertEquals(0, game.getScore());
                assertEquals(9, game.getCurrentLives());
                assertEquals(CORRECT_ANSWER, outputData.getCorrectAnswer());
                assertFalse(outputData.isUserAnswerCorrect());
                assertEquals(INCORRECT_ANSWER, game.getCurrentRound().getUserAnswer());
            }
        };

        SubmitAnswerInputBoundary interactor = new SubmitAnswerInteractor(gameDao, correctPresenter);
        interactor.execute(inputData);
    }
}
