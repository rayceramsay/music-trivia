package use_case.get_loadable_games;

import data_access.InMemoryGameDataAccessObject;
import entity.*;

import org.junit.Test;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class GetLoadableGamesInteractorTest {
    private GetLoadableGamesGameDataAccessInterface gameRepository;

    /**
     * Initializes gameRepository with dummy data.
     *
     * @param finishAllGames whether all games should be marked as finished (true) or only some of them (false)
     */
    private void setupGameRepository(boolean finishAllGames) {
        gameRepository = new InMemoryGameDataAccessObject();
        RoundFactory roundFactory = new CommonRoundFactory();

        for (int i = 0; i < 6; i++) {
            Game game = new CommonGame("hip hop", "hard", 1, 3);
            Round round = roundFactory.createHardRound("hip hop");
            game.setCurrentRound(round);

            if (finishAllGames || i % 2 == 1) {
                round.setUserAnswer("answer");
                game.setFinishedAt(LocalDateTime.now());
            }

            gameRepository.save(game);
        }
    }

    /**
     * Test that the interactor outputs the appropriate games when there exist loadable games.
     */
    @Test
    public void loadableGamesExistTest() {
        setupGameRepository(false);

        GetLoadableGamesOutputBoundary presenter = new GetLoadableGamesOutputBoundary() {
            @Override
            public void prepareGamesExistView(GetLoadableGamesOutputData outputData) {
                List<Map<String, String>> gamesData = outputData.getLoadableGamesData();

                assertEquals(3, gamesData.size());
                assertNotEquals(gamesData.get(0), gamesData.get(1));
                assertNotEquals(gamesData.get(1), gamesData.get(2));
                assertNotEquals(gamesData.get(2), gamesData.get(0));
            }

            @Override
            public void prepareNoGamesExistView(String message) {
                fail("prepareNoGamesExistView is unexpected.");
            }
        };

        GetLoadableGamesInputBoundary interactor = new GetLoadableGamesInteractor(presenter, gameRepository);
        interactor.execute();
    }

    /**
     * Test that the interactor outputs an empty list when there are no loadable games.
     */
    @Test
    public void noLoadableGamesExistTest() {
        setupGameRepository(true);

        GetLoadableGamesOutputBoundary presenter = new GetLoadableGamesOutputBoundary() {
            @Override
            public void prepareGamesExistView(GetLoadableGamesOutputData outputData) {
                fail("prepareGamesExistView is unexpected.");
            }

            @Override
            public void prepareNoGamesExistView(String message) {
                assertNotNull(message);
            }
        };

        GetLoadableGamesInputBoundary interactor = new GetLoadableGamesInteractor(presenter, gameRepository);
        interactor.execute();
    }

    /**
     * Test that the interactor outputs all the necessary information about the loadable games.
     */
    @Test
    public void correctGamesDataTest() {
        setupGameRepository(false);

        GetLoadableGamesOutputBoundary presenter = new GetLoadableGamesOutputBoundary() {
            @Override
            public void prepareGamesExistView(GetLoadableGamesOutputData outputData) {
                String[] requiredKeys = new String[]{"gameID", "difficulty", "genre", "initialLives", "currentLives",
                        "maxRounds", "currentRoundNumber", "createdAt"};
                List<String> gamesDataKeys = new ArrayList<>(outputData.getLoadableGamesData().get(0).keySet());

                assertTrue(gamesDataKeys.containsAll(List.of(requiredKeys)));
            }

            @Override
            public void prepareNoGamesExistView(String message) {
                fail("prepareNoGamesExistView is unexpected.");
            }
        };

        GetLoadableGamesInputBoundary interactor = new GetLoadableGamesInteractor(presenter, gameRepository);
        interactor.execute();
    }
}
