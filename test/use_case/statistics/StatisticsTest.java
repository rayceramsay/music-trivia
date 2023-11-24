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
    @Test
    public void averageInitialLivesTest(){
        assertEquals(10.0, gameDataAccessObject.avgStats().get("Average Initial Lives"));
    }
    @Test
    public void averageScoreTest() {
        game.setScore(10);
        assertEquals(10.0, gameDataAccessObject.avgStats().get("Average Initial Lives"));
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



}
