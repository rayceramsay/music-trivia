package use_case.statistics;

import data_access.game_data.GameDataAccessInterface;
import data_access.game_data.InMemoryGameDataAccessObject;
import entity.*;
import interface_adapter.ViewManagerModel;
import interface_adapter.exit_round.ExitRoundPresenter;
import interface_adapter.exit_round.ExitRoundViewModel;
import interface_adapter.load_game.LoadGameViewModel;
import interface_adapter.round.RoundViewModel;
import interface_adapter.statistics.StatisticsPresenter;
import interface_adapter.statistics.StatisticsState;
import interface_adapter.statistics.StatisticsViewModel;
import interface_adapter.toggle_audio.ToggleAudioViewModel;
import org.junit.Before;
import org.junit.Test;
import use_case.exit_round.ExitRoundInputBoundary;
import use_case.exit_round.ExitRoundInputData;
import use_case.exit_round.ExitRoundInteractor;
import use_case.exit_round.ExitRoundOutputBoundary;
import view.RoundView;

import static org.junit.Assert.*;

public class StatisticsTest {

    private final int INITIAL_LIVES = 10;
    private GameDataAccessInterface gameDataAccessObject;
    private Game game;

    @Before
    public void init() {
        gameDataAccessObject = new InMemoryGameDataAccessObject();
    }

    @Test
    public void failTest() {
        StatisticsOutputBoundary statsPresenter = new StatisticsOutputBoundary() {
            @Override
            public void prepareView(StatisticsOutputData outputData) {
                assertFalse(outputData.hasStats());
            }
        };
        StatisticsInputBoundary interactor = new StatisticsInteractor(gameDataAccessObject, statsPresenter);
        interactor.execute();
    }

    /**
     * Basic functionality tests.
     */

    @Test
    public void averageInitialLivesTest() {
        game = new CommonGame("hip-hop", "hard", 15, INITIAL_LIVES);
        gameDataAccessObject.save(game);
        assertEquals(10, gameDataAccessObject.avgStats().getAverageInitialLives());
    }

    @Test
    public void averageScoreTest() {
        game = new CommonGame("hip-hop", "hard", 15, INITIAL_LIVES);
        game.setScore(10);
        gameDataAccessObject.save(game);
        assertEquals(10, gameDataAccessObject.avgStats().getAverageScore());
    }

    @Test
    public void averageRoundPlayedTest(){
        game = new CommonGame("hip-hop", "hard", 15, INITIAL_LIVES);
        gameDataAccessObject.save(game);
        assertEquals(0, gameDataAccessObject.avgStats().getAverageRoundsPlayed());
        Round round = null;
        game.setCurrentRound(round);
        game.setCurrentRound(round);
        assertEquals(2, gameDataAccessObject.avgStats().getAverageRoundsPlayed());
    }

    @Test
    public void commonDifficultyTest() {
        game = new CommonGame("hip-hop", "hard", 15, INITIAL_LIVES);
        gameDataAccessObject.save(game);
        assertEquals("hard", gameDataAccessObject.avgStats().getTopDifficulty().toLowerCase());
    }

    @Test
    public void commonGenreTest() {
        game = new CommonGame("hip-hop", "hard", 15, INITIAL_LIVES);
        gameDataAccessObject.save(game);
        assertEquals("hip-hop", gameDataAccessObject.avgStats().getTopGenre().toLowerCase());
        game = new CommonGame("pop", "hard", 15, INITIAL_LIVES);
        gameDataAccessObject.save(game);
        game = new CommonGame("pop", "hard", 15, INITIAL_LIVES);
        gameDataAccessObject.save(game);
        assertEquals("pop", gameDataAccessObject.avgStats().getTopGenre().toLowerCase());
    }

    /**
     * Output data/presenter/interactor test to assure coverage of use_case.statistics
     */
    @Test
    public void coverageTest() {
        game = new CommonGame("rock", "hard", 1, INITIAL_LIVES);
        gameDataAccessObject.save(game);
        StatisticsOutputBoundary statsPresenter = new StatisticsOutputBoundary() {
            @Override
            public void prepareView(StatisticsOutputData outputData) {
                assertEquals("rock", outputData.getCommonGameGenre().toLowerCase());
                assertEquals(10, outputData.getAverageLives());
                outputData.setAverageScore(100);
                assertEquals(100, outputData.getAverageScore());
                assertEquals("hard", outputData.getCommonGameDifficulty().toLowerCase());
                assertEquals(0, outputData.getAverageRoundsPlayed());
            }
        };
        StatisticsInputBoundary interactor = new StatisticsInteractor(gameDataAccessObject, statsPresenter);
        interactor.execute();
    }

    @Test
    public void furtherTesting(){
        StatisticsViewModel viewModel = new StatisticsViewModel(StatisticsViewModel.STATE_PROPERTY);
        StatisticsOutputBoundary presenter = new StatisticsPresenter(viewModel);
        StatisticsInputBoundary interactor = new StatisticsInteractor(gameDataAccessObject, presenter);

        interactor.execute();

        StatisticsState state = viewModel.getState();
        assert state.getStatsMessage().contains("No stats have been recorded");

        game = new CommonGame("Rock", "hard", 1, INITIAL_LIVES);
        gameDataAccessObject.save(game);

        interactor.execute();

        assert state.getStatsMessage().contains("Here are your lifetime statistics");

    }
}
