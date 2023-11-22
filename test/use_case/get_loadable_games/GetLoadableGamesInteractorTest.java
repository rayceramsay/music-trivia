package use_case.get_loadable_games;

import data_access.InMemoryGameDataAccessObject;
import entity.*;

import org.junit.Test;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class GetLoadableGamesInteractorTest {

    private GetLoadableGamesGameDataAccessInterface gameRepository;
    private List<Game> loadableGames;

    /**
     * Initializes gameRepository with dummy data and populates loadableGames with loadable games in order of game
     * creation.
     *
     * @param finishAllGames whether all games should be marked as finished (true) or only some of them (false)
     */
    private void setupGameRepository(boolean finishAllGames) {
        gameRepository = new InMemoryGameDataAccessObject();
        loadableGames = new ArrayList<>();
        RoundFactory roundFactory = new CommonRoundFactory();

        for (int i = 0; i < 6; i++) {
            Game game = new CommonGame("hip hop", "hard", 1, 3);
            Round round = roundFactory.createHardRound("hip hop");
            game.setCurrentRound(round);

            if (finishAllGames || i % 2 == 1) {
                round.setUserAnswer("answer");
                game.setFinishedAt(LocalDateTime.now());
            } else {
                loadableGames.add(game);
            }

            gameRepository.save(game);
        }
    }

    /**
     * Test that the interactor outputs the data for loadable games in descending order of creation time when loadable
     * games exist.
     */
    @Test
    public void gamesDataExistAndSortedTest() {
        setupGameRepository(false);

        GetLoadableGamesOutputBoundary presenter = new GetLoadableGamesOutputBoundary() {
            @Override
            public void prepareGamesExistView(GetLoadableGamesOutputData outputData) {
                List<Map<String, String>> gamesData = outputData.getLoadableGamesData();

                assertEquals(loadableGames.size(), gamesData.size());

                for (int i = 0, j = loadableGames.size() - 1;
                     i < loadableGames.size() && j >= 0;
                     i++, j--) {
                    assertEquals(loadableGames.get(j).getID(), gamesData.get(i).get("ID"));
                }
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
     * Test that the interactor outputs necessary and accurate information about the loadable games.
     */
    @Test
    public void correctGamesDataTest() {
        setupGameRepository(false);

        GetLoadableGamesOutputBoundary presenter = new GetLoadableGamesOutputBoundary() {
            @Override
            public void prepareGamesExistView(GetLoadableGamesOutputData outputData) {
                Game exampleGame = loadableGames.get(0);
                Map<String, String> exampleGameData = outputData.getLoadableGamesData()
                        .stream()
                        .filter(data -> data.get("ID").equals(exampleGame.getID()))
                        .findFirst()
                        .orElseThrow();

                assertEquals(exampleGame.getDifficulty(), exampleGameData.get("difficulty"));
                assertEquals(exampleGame.getGenre(), exampleGameData.get("genre"));
                assertEquals(String.valueOf(exampleGame.getCurrentLives()), exampleGameData.get("currentLives"));
                assertEquals(String.valueOf(exampleGame.getInitialLives()), exampleGameData.get("initialLives"));
                assertEquals(String.valueOf(exampleGame.getRoundsPlayed()), exampleGameData.get("currentRoundNumber"));
                assertEquals(String.valueOf(exampleGame.getMaxRounds()), exampleGameData.get("maxRounds"));
                assertNotNull(exampleGameData.get("createdAt"));
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
     * Test that the interactor outputs an error message when no loadable games exist.
     */
    @Test
    public void noLoadableGamesExistTest() {
        setupGameRepository(true);

        GetLoadableGamesOutputBoundary presenter = new GetLoadableGamesOutputBoundary() {
            @Override
            public void prepareGamesExistView(GetLoadableGamesOutputData outputData) { fail("prepareGamesExistView is unexpected."); }

            @Override
            public void prepareNoGamesExistView(String message) {
                assertNotNull(message);
            }
        };

        GetLoadableGamesInputBoundary interactor = new GetLoadableGamesInteractor(presenter, gameRepository);
        interactor.execute();
    }
}
