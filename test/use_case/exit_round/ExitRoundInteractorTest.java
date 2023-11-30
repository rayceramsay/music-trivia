package use_case.exit_round;

import data_access.InMemoryGameDataAccessObject;
import entity.CommonGame;
import entity.Game;
import entity.MockRoundFactory;
import entity.RoundFactory;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ExitRoundInteractorTest {

    private boolean presenterGetsCalled;
    private ExitRoundGameDataAccessInterface gameRepository;
    private Game game;

    /**
     * Initialize each test with a game DAO pre-populated with an in-progress game.
     */
    @Before
    public void init() {
        presenterGetsCalled = false;
        gameRepository = new InMemoryGameDataAccessObject();

        String genre = "country";
        game = new CommonGame(genre, "hard", 10, 10);
        RoundFactory roundFactory = new MockRoundFactory();
        game.setCurrentRound(roundFactory.createHardRound(genre));
        gameRepository.save(game);
    }

    /**
     * Test that the round song audio isn't playing.
     */
    @Test
    public void songIsNotPlayingTest() {
        game.getCurrentRound().getSong().getAudio().play();

        ExitRoundOutputBoundary presenter = new ExitRoundOutputBoundary() {
            @Override
            public void prepareView() {
                presenterGetsCalled = true;
                assertFalse(game.getCurrentRound().getSong().getAudio().isPlaying());
            }
        };
        ExitRoundInputBoundary interactor = new ExitRoundInteractor(presenter, gameRepository);
        ExitRoundInputData inputData = new ExitRoundInputData(game.getID());

        interactor.execute(inputData);

        assertTrue(presenterGetsCalled);
    }
}
