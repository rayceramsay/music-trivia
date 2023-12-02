package use_case.finish_round;

import data_access.game_data.GameDataAccessInterface;
import data_access.game_data.InMemoryGameDataAccessObject;
import entity.*;
import interface_adapter.ViewManagerModel;
import interface_adapter.create_game.CreateGamePresenter;
import interface_adapter.create_game.CreateGameViewModel;
import interface_adapter.finish_round.FinishRoundController;
import interface_adapter.finish_round.FinishRoundPresenter;
import interface_adapter.finish_round.FinishRoundViewModel;
import interface_adapter.game_over.GameOverState;
import interface_adapter.game_over.GameOverViewModel;
import interface_adapter.round.RoundState;
import interface_adapter.round.RoundViewModel;
import org.junit.Before;
import org.junit.Test;
import use_case.create_game.CreateGameInputBoundary;
import use_case.create_game.CreateGameInputData;
import use_case.create_game.CreateGameInteractor;
import view.GameOverView;
import view.RoundView;

import java.util.Objects;

import static org.junit.Assert.*;

public class FinishRoundInteractorTest {

    private GameDataAccessInterface gameDataAccessObject;
    private Round round;
    private RoundFactory roundFactory;

    /**
     * Initialize each test to have an existing DAO, song and round object.
     */
    @Before
    public void init() {
        gameDataAccessObject = new InMemoryGameDataAccessObject();
        roundFactory = new MockRoundFactory();
        round = roundFactory.createHardRound("pop");
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
                assertEquals(game.getScore(), outputData.getScore());

                // Verify output data
                assertEquals(game.getScore(), outputData.getScore());
            }
            @Override
            public void prepareNextRoundView(FinishRoundOutputData outputData) {
                fail("Unexpected!");
            }
        };

        FinishRoundInputBoundary interactor = new FinishRoundInteractor(gameOverPresenter, gameDataAccessObject, roundFactory);
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

        FinishRoundInputBoundary interactor = new FinishRoundInteractor(gameOverPresenter, gameDataAccessObject, roundFactory);
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

        FinishRoundInputBoundary interactor = new FinishRoundInteractor(gameOverPresenter, gameDataAccessObject, roundFactory);
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

        FinishRoundInputBoundary interactor = new FinishRoundInteractor(gameOverPresenter, gameDataAccessObject, roundFactory);
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
                assertEquals(game.getCurrentRound().getClass(), MultipleChoiceRound.class);
                MultipleChoiceRound currRound =  (MultipleChoiceRound) game.getCurrentRound();
                assertEquals(4, currRound.getMultipleChoiceAnswers().size());

                // Verify output data
                assertEquals(game.getGenre(), outputData.getGenre());
                assertEquals(outputData.getRoundNumber(), 2);
                assertEquals(outputData.getLives(), game.getCurrentLives());
            }
        };

        FinishRoundInputBoundary interactor = new FinishRoundInteractor(gameOverPresenter, gameDataAccessObject, roundFactory);
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
        Game game = new CommonGame("pop", "easy", 4, initialLives);
        game.setCurrentRound(round);
        round.setUserAnswer("wrong");
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
                assertEquals(game.getCurrentRound().getClass(), MultipleChoiceRound.class);
                MultipleChoiceRound currRound =  (MultipleChoiceRound) game.getCurrentRound();
                assertEquals(2, currRound.getMultipleChoiceAnswers().size());
                assertEquals("Question", currRound.getQuestion());
                assertEquals("title", currRound.getSong().getTitle());
                assertEquals(round.getCorrectAnswer(), currRound.getCorrectAnswer());
                assertEquals(false, currRound.isUserAnswerCorrect());


                // Verify output data
                assertEquals(game.getGenre(), outputData.getGenre());
                assertEquals(outputData.getRoundNumber(), 2);
                assertEquals(game.getCurrentLives(), outputData.getLives());

            }
        };

        FinishRoundInputBoundary interactor = new FinishRoundInteractor(gameOverPresenter, gameDataAccessObject, roundFactory);
        interactor.execute(inputData);
    }
    @Test
    public void nextRoundandGameOverTest() {
        // Game will create a next round
        Game game = new CommonGame("pop", "easy", 2, 2);
        game.setCurrentRound(round);
        round.setUserAnswer("answer");
        gameDataAccessObject.save(game);

        FinishRoundInputData inputData = new FinishRoundInputData(game.getID());

        ViewManagerModel viewManagerModel = new ViewManagerModel();
        RoundViewModel roundViewModel = new RoundViewModel(RoundView.VIEW_NAME);
        GameOverViewModel gameOverViewModel = new GameOverViewModel(GameOverView.VIEW_NAME);
        FinishRoundViewModel finishRoundViewModel = new FinishRoundViewModel(FinishRoundViewModel.STATE_PROPERTY);

        FinishRoundOutputBoundary gameOverPresenter = new FinishRoundPresenter(viewManagerModel, gameOverViewModel, roundViewModel, finishRoundViewModel);
        FinishRoundInputBoundary interactor = new FinishRoundInteractor(gameOverPresenter, gameDataAccessObject, roundFactory);
        interactor.execute(inputData);

        RoundState state = roundViewModel.getState();
        GameOverState gameOverState = gameOverViewModel.getState();

        assert state.getScore() == game.getScore();

        //Game reaches max rounds so it will end
        roundFactory = new MockRoundFactory();
        round = roundFactory.createHardRound("pop");
        game.setCurrentRound(round);
        gameDataAccessObject.save(game);
        interactor.execute(inputData);

        assert state.isMultipleChoiceRound();
        assert state.getMultipleChoiceOptions().size() == 2;
        assert gameOverState.getScore() == 0;
        assert game.isGameOver();
        assert viewManagerModel.getActiveView() == "game over";
    }
}
