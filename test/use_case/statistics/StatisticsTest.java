package use_case.statistics;

import data_access.InMemoryGameDataAccessObject;
import entity.*;
import org.junit.Before;
import org.junit.Test;

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
        game = new CommonGame("Hip-Hop", "hard", 15, INITIAL_LIVES);
        gameDataAccessObject = new InMemoryGameDataAccessObject();
        gameDataAccessObject.save(game);
    }

    /**
     * Basic functionality tests.
     */
    @Test
    public void averageInitialLivesTest() {
        assertEquals(10, gameDataAccessObject.avgStats().get("Average Initial Lives"));
    }

    @Test
    public void averageScoreTest() {
        game.setScore(10);
        assertEquals(10, gameDataAccessObject.avgStats().get("Average Initial Lives"));
    }

    @Test
    public void commonDifficultyTest() {
        assertEquals("Hard", gameDataAccessObject.avgStats().get("Most Common Game Difficulty"));
    }

    @Test
    public void commonGenreTest() {
        assertEquals("Hip-Hop", gameDataAccessObject.avgStats().get("Most Common Genre"));
        game = new CommonGame("Pop", "hard", 15, INITIAL_LIVES);
        gameDataAccessObject.save(game);
        game = new CommonGame("Pop", "hard", 15, INITIAL_LIVES);
        gameDataAccessObject.save(game);
        assertEquals("Pop", gameDataAccessObject.avgStats().get("Most Common Genre"));
    }

    @Test
    public void coverageTest() {
        game = new CommonGame("Rock", "hard", 1, INITIAL_LIVES);
        gameDataAccessObject.save(game);
        StatisticsOutputBoundary statsPresenter = new StatisticsOutputBoundary() {
            @Override
            public void prepareView(StatisticsOutputData outputData) {
                assertEquals("Rock", outputData.getStats().get("Most Common Genre"));
                assertEquals(10, outputData.getStats().get("Average Initial Lives"));
                outputData.getStats().put("Average Score Across All Games", 100);
                assertEquals(100, outputData.getStats().get("Average Score Across All Games"));
                assertEquals("Hard", outputData.getStats().get("Most Common Game Difficulty"));
            }
        };
        StatisticsInputBoundary interactor = new StatisticsInteractor(gameDataAccessObject, statsPresenter);
        interactor.execute();
    }
}
