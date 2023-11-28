package use_case.statistics;

import data_access.InMemoryGameDataAccessObject;
import entity.*;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.*;
public class StatisticsTest {
    /**
     * Initialize each test to have an existing game with the current round already set and a data access object.
     */
    private final int INITIAL_LIVES = 10;
    private StatisticsDataAccessInterface gameDataAccessObject;
    private Game game;

    @Before
    public void init() {
        gameDataAccessObject = new InMemoryGameDataAccessObject();
    }
    /**
     * Basic functionality tests.
     */
    @Test
    public void averageInitialLivesTest() {
        game = new CommonGame("Hip-Hop", "hard", 15, INITIAL_LIVES);
        gameDataAccessObject.save(game);
        assertEquals(10, gameDataAccessObject.avgStats().getAverageInitialLives());
    }

    @Test
    public void averageScoreTest() {
        game = new CommonGame("Hip-Hop", "hard", 15, INITIAL_LIVES);
        game.setScore(10);
        gameDataAccessObject.save(game);
        assertEquals(10, gameDataAccessObject.avgStats().getAverageScore());
    }
    @Test
    public void averageRoundPlayedTest(){
        game = new CommonGame("Hip-Hop", "hard", 15, INITIAL_LIVES);
        gameDataAccessObject.save(game);
        assertEquals(0, gameDataAccessObject.avgStats().getAverageRoundsPlayed());
        Round round = null;
        game.setCurrentRound(round);
        game.setCurrentRound(round);
        assertEquals(2, gameDataAccessObject.avgStats().getAverageRoundsPlayed());
    }
    @Test
    public void commonDifficultyTest() {
        game = new CommonGame("Hip-Hop", "hard", 15, INITIAL_LIVES);
        gameDataAccessObject.save(game);
        assertEquals("Hard", gameDataAccessObject.avgStats().getTopDifficulty());
    }

    @Test
    public void commonGenreTest() {
        game = new CommonGame("Hip-Hop", "hard", 15, INITIAL_LIVES);
        gameDataAccessObject.save(game);
        assertEquals("Hip-Hop", gameDataAccessObject.avgStats().getTopGenre());
        game = new CommonGame("Pop", "hard", 15, INITIAL_LIVES);
        gameDataAccessObject.save(game);
        game = new CommonGame("Pop", "hard", 15, INITIAL_LIVES);
        gameDataAccessObject.save(game);
        assertEquals("Pop", gameDataAccessObject.avgStats().getTopGenre());
    }
    /**
     * Output data/presenter/interactor test to assure coverage of use_case.statistics
     */
    @Test
    public void coverageTest() {
        game = new CommonGame("Rock", "hard", 1, INITIAL_LIVES);
        gameDataAccessObject.save(game);
        StatisticsOutputBoundary statsPresenter = new StatisticsOutputBoundary() {
            @Override
            public void prepareView(StatisticsOutputData outputData) {
                assertEquals("Rock", outputData.getCommonGameGenre());
                assertEquals(10, outputData.getAverageLives());
                outputData.setAverageScore(100);
                assertEquals(100, outputData.getAverageScore());
                assertEquals("Hard", outputData.getCommonGameDifficulty());
            }
        };
        StatisticsInputBoundary interactor = new StatisticsInteractor(gameDataAccessObject, statsPresenter);
        interactor.execute();
    }
}
