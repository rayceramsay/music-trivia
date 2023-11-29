package use_case.finish_round;

import data_access.InMemoryGameDataAccessObject;
import entity.*;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class FinishRoundInteractorTest {
    private FinishRoundGameDataAccessInterface gameDataAccessObject;
    private Round round;

    /**
     * Initialize each test to have an existing DAO, song and round object.
     */
    @Before
    public void init() {
        final String CORRECT_ANSWER = "SpotifyBanditsTestCorrectAnswer";
        Song song = new CommonSong(CORRECT_ANSWER, "me", new FileMP3PlayableAudio("path/song.mp3"));
        round = new TextInputRound(song, "What song is this?", CORRECT_ANSWER);
        gameDataAccessObject = new InMemoryGameDataAccessObject();
    }

    /**
     * Test that the game ends when the max number of rounds has been reached even when the user has more than 0 lives.
     * Verify in test:
     * - Game is over
     * - Output data matches game data
     */
    @Test
    public void gameFinishedWithMaxRoundsTest() {
        int initialLives = 3;
        int maxRounds = 1;
        Game game = new CommonGame("pop", "hard", maxRounds, initialLives);
        game.setCurrentRound(round);
        round.setUserAnswer("answer");
        gameDataAccessObject.save(game);

        FinishRoundInputData inputData = new FinishRoundInputData(game.getID());
        FinishRoundOutputBoundary gameOverPresenter = new FinishRoundOutputBoundary() {
            @Override
            public void prepareGameOverView(FinishRoundOutputData outputData) {
                // Verify game is over atributes
                assertTrue(game.isGameOver());
                assertEquals(game.getRoundsPlayed(), game.getMaxRounds());
                assertNotEquals(0, game.getCurrentLives());
                assertNotNull(game.getFinishedAt());

                // Verify output data
                assertEquals(game.getScore(), outputData.getScore());
            }
            @Override
            public void prepareNextRoundView(FinishRoundOutputData outputData) {
                fail("Unexpected!");
            }
        };

        FinishRoundInputBoundary interactor = new FinishRoundInteractor(gameOverPresenter, gameDataAccessObject);
        interactor.execute(inputData);
    }

    /**
     * Test that the game ends when the user has 1 life and submits an incorrect answer while the max number of rounds
     * has not been reached.
     * Verify in test:
     * - Game is over
     * - Output data matches game data
     */
    @Test
    public void gameFinishedWithNoLivesTest() {
        int initialLives = 1;
        int maxRounds = 10;
        Game game = new CommonGame("pop", "hard", maxRounds, initialLives);
        game.setCurrentRound(round);
        game.decrementLives();
        gameDataAccessObject.save(game);

        FinishRoundInputData inputData = new FinishRoundInputData(game.getID());
        FinishRoundOutputBoundary gameOverPresenter = new FinishRoundOutputBoundary() {
            @Override
            public void prepareGameOverView(FinishRoundOutputData outputData) {
                // Verify game is over atrributes
                assertTrue(game.isGameOver());
                assertNotEquals(game.getRoundsPlayed(), game.getMaxRounds());
                assertEquals(0, game.getCurrentLives());
                assertNotNull(game.getFinishedAt());

                // Verify output data
                assertEquals(game.getScore(), outputData.getScore());
            }
            @Override
            public void prepareNextRoundView(FinishRoundOutputData outputData) {
                fail("Unexpected!");
            }
        };

        FinishRoundInputBoundary interactor = new FinishRoundInteractor(gameOverPresenter, gameDataAccessObject);
        interactor.execute(inputData);
    }

    /**
     * This is an edge case test that tests the game ends when the user has 1 life and submits an incorrect answer
     * while the max number of rounds has been reached.
     * Verify in test:
     * - Game is over
     * - Output data matches game data
     */
    @Test
    public void gameFinishedWithNoLivesAndMaxRoundsTest() {
        int initialLives = 1;
        int maxRounds = 1;
        Game game = new CommonGame("pop", "hard", maxRounds, initialLives);
        game.setCurrentRound(round);
        game.decrementLives();
        gameDataAccessObject.save(game);

        FinishRoundInputData inputData = new FinishRoundInputData(game.getID());
        FinishRoundOutputBoundary gameOverPresenter = new FinishRoundOutputBoundary() {
            @Override
            public void prepareGameOverView(FinishRoundOutputData outputData) {
                // Verify game is over atrributes
                assertTrue(game.isGameOver());
                assertEquals(game.getRoundsPlayed(), game.getMaxRounds());
                assertEquals(0, game.getCurrentLives());
                assertNotNull(game.getFinishedAt());

                // Verify output data
                assertEquals(game.getScore(), outputData.getScore());
            }
            @Override
            public void prepareNextRoundView(FinishRoundOutputData outputData) {
                fail("Unexpected!");
            }
        };

        FinishRoundInputBoundary interactor = new FinishRoundInteractor(gameOverPresenter, gameDataAccessObject);
        interactor.execute(inputData);
    }

    /**
     * Test that after a round where the user's lives is not 0 and the game has not reached
     * max round that a next 'hard' round (text input round) is generated and set as the current round.
     * Verify in test:
     *  - Game is not over
     *  - New round has been properly created and game has updated accordingly
     *  - Output data is same as game data
     */
    @Test
    public void nextHardRoundGeneratedTest() {
        int initialLives = 3;
        int maxRounds = 10;
        Game game = new CommonGame("pop", "hard", maxRounds, initialLives);
        game.setCurrentRound(round);
        gameDataAccessObject.save(game);

        FinishRoundInputData inputData = new FinishRoundInputData(game.getID());
        FinishRoundOutputBoundary gameOverPresenter = new FinishRoundOutputBoundary() {
            @Override
            public void prepareGameOverView(FinishRoundOutputData outputData) {
                fail("Unexpected!");
            }
            @Override
            public void prepareNextRoundView(FinishRoundOutputData outputData) {
                // Verify game is not over attributes
                assertFalse(game.isGameOver());
                assertNotEquals(game.getRoundsPlayed(), game.getMaxRounds());
                assertNotEquals(0, game.getCurrentLives());
                assertNull(game.getFinishedAt());

                // Verify info about new current round
                assertEquals(game.getRoundsPlayed(), 2);
                assertNotEquals(game.getCurrentRound(), round);
                assertEquals(game.getCurrentRound().getClass(), TextInputRound.class);

                // Verify output data
                assertEquals(game.getGenre(), outputData.getGenre());
                assertEquals(outputData.getRoundNumber(), 2);
                assertEquals(outputData.getLives(), game.getCurrentLives());
            }
        };

        FinishRoundInputBoundary interactor = new FinishRoundInteractor(gameOverPresenter, gameDataAccessObject);
        interactor.execute(inputData);
    }

    /**
     * Test that after a round where the user's lives is not 0 and the game has not reached
     * max round that a next 'medium' round (four mc round) is generated and set as the current round.
     * Verify in test:
     *  - Game is not over
     *  - New round has been properly created and game has updated accordingly
     *  - Output data is same as game data
     */
    @Test
    public void nextMediumRoundGeneratedTest() {
        int initialLives = 3;
        int maxRounds = 10;
        Game game = new CommonGame("pop", "medium", maxRounds, initialLives);
        game.setCurrentRound(round);
        gameDataAccessObject.save(game);

        FinishRoundInputData inputData = new FinishRoundInputData(game.getID());
        FinishRoundOutputBoundary gameOverPresenter = new FinishRoundOutputBoundary() {
            @Override
            public void prepareGameOverView(FinishRoundOutputData outputData) {
                fail("Unexpected!");
            }
            @Override
            public void prepareNextRoundView(FinishRoundOutputData outputData) {
                // Verify game is not over attributes
                assertFalse(game.isGameOver());
                assertNotEquals(game.getRoundsPlayed(), game.getMaxRounds());
                assertNotEquals(0, game.getCurrentLives());
                assertNull(game.getFinishedAt());

                // Verify info about new current round
                assertEquals(2, game.getRoundsPlayed());
                assertNotEquals(game.getCurrentRound(), round);
                assertEquals(game.getCurrentRound().getClass(), FourMultipleChoiceRound.class);

                // Verify output data
                assertEquals(game.getGenre(), outputData.getGenre());
                assertEquals(outputData.getRoundNumber(), 2);
                assertEquals(outputData.getLives(), game.getCurrentLives());
            }
        };

        FinishRoundInputBoundary interactor = new FinishRoundInteractor(gameOverPresenter, gameDataAccessObject);
        interactor.execute(inputData);
    }

    /**
     * Test that after a round where the user's lives is not 0 and the game has not reached
     * max round that a next 'easy' round (2 mc round) is generated and set as the current round.
     * Verify in test:
     *  - Game is not over
     *  - New round has been properly created and game has updated accordingly
     *  - Output data is same as game data
     */
    @Test
    public void nextEasyRoundGeneratedTest() {
        int initialLives = 3;
        int maxRounds = 10;
        Game game = new CommonGame("pop", "easy", maxRounds, initialLives);
        game.setCurrentRound(round);
        gameDataAccessObject.save(game);

        FinishRoundInputData inputData = new FinishRoundInputData(game.getID());
        FinishRoundOutputBoundary gameOverPresenter = new FinishRoundOutputBoundary() {
            @Override
            public void prepareGameOverView(FinishRoundOutputData outputData) {
                fail("Unexpected!");
            }
            @Override
            public void prepareNextRoundView(FinishRoundOutputData outputData) {
                // Verify game is not over attributes
                assertFalse(game.isGameOver());
                assertNotEquals(game.getRoundsPlayed(), game.getMaxRounds());
                assertNotEquals(0, game.getCurrentLives());
                assertNull(game.getFinishedAt());

                // Verify info about new current round
                assertEquals(game.getRoundsPlayed(), 2);
                assertNotEquals(game.getCurrentRound(), round);
                assertEquals(game.getCurrentRound().getClass(), TwoMultipleChoiceRound.class);

                // Verify output data
                assertEquals(game.getGenre(), outputData.getGenre());
                assertEquals(outputData.getRoundNumber(), 2);
                assertEquals(game.getCurrentLives(), outputData.getLives());

            }
        };

        FinishRoundInputBoundary interactor = new FinishRoundInteractor(gameOverPresenter, gameDataAccessObject);
        interactor.execute(inputData);
    }
}
