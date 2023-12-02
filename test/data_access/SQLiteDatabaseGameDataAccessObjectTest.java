package data_access;

import entity.*;
import io.github.cdimascio.dotenv.Dotenv;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class SQLiteDatabaseGameDataAccessObjectTest {

    private final static String TEST_DATABASE_PATH = Dotenv.load().get("SQLITE_DB_PATH_TEST");

    private SQLiteDatabaseGameDataAccessObject gameRepository;

    /**
     * Initialize game repository and ensure that it gets cleared before every test.
     */
    @Before
    public void init() {
        gameRepository = new SQLiteDatabaseGameDataAccessObject(TEST_DATABASE_PATH);
        gameRepository.clear();
    }

    /**
     * Test that a game saved in one instance of the game repository exists in a different instance.
     */
    @Test
    public void persistenceTest() {
        Game mockedGame = createMockGame();
        gameRepository.save(mockedGame);
        SQLiteDatabaseGameDataAccessObject differentGameRepository = new SQLiteDatabaseGameDataAccessObject(TEST_DATABASE_PATH);
        assertTrue(differentGameRepository.gameExists(mockedGame));
    }

    /**
     * Test that a game exists in the game repository after:
     *   - Saving for the first time
     *   - Saving when it has already been saved
     */
    @Test
    public void saveTest() {
        Game mockedGame = createMockGame();
        assertFalse(gameRepository.gameExists(mockedGame));
        gameRepository.save(mockedGame);
        assertTrue(gameRepository.gameExists(mockedGame));
        gameRepository.save(mockedGame);
        assertTrue(gameRepository.gameExists(mockedGame));
    }

    /**
     * Test that a previously saved game can be fetched again by its ID and has the same properties and rounds.
     */
    @Test
    public void getExistentGameByIDTest() {
        Game mockedGame = createMockGame();
        gameRepository.save(mockedGame);
        Game copy = gameRepository.getGameByID(mockedGame.getID());
        assertGamesAreDeepCopies(mockedGame, copy);
    }

    /**
     * Test that trying to fetch a non-existent game returns null.
     */
    @Test
    public void getNonExistentGameByIDTest() {
        assertNull(gameRepository.getGameByID("notARealID"));
    }

    /**
     * Test that getLoadableGames returns the proper list of games when there are:
     *   - No games saved
     *   - Some loadable games saved
     *   - Only non-loadable games saved
     */
    @Test
    public void getLoadableGamesTest() {
        List<Game> initialLoadableGames = gameRepository.getLoadableGames();
        assertEquals(0, initialLoadableGames.size());

        Game loadableGame = createMockGame();
        assertFalse(loadableGame.isGameOver());  // i.e. is loadable
        gameRepository.save(loadableGame);
        List<Game> someLoadableGames = gameRepository.getLoadableGames();
        assertEquals(1, someLoadableGames.size());
        assertGamesAreDeepCopies(loadableGame, someLoadableGames.get(0));

        loadableGame.getCurrentRound().setUserAnswer("wrongAnswer");
        loadableGame.decrementLives();
        loadableGame.setFinishedAt(LocalDateTime.now());
        assertTrue(loadableGame.isGameOver());  // i.e. is not loadable
        gameRepository.save(loadableGame);
        List<Game> noLoadableGames = gameRepository.getLoadableGames();
        assertEquals(0, noLoadableGames.size());
    }

    /**
     * Test that avgStats returns the expected lifetime statistics when games have been saved.
     */
    @Test
    public void avgStatsWithGamesPlayedTest() {
        Game finishedGame1 = new CommonGame("id1", "pop", "hard", 6, 5, 3, 3, LocalDateTime.now(), LocalDateTime.now());
        addCorrectlyAnsweredRoundToGame(finishedGame1, 1);
        addIncorrectlyAnsweredRoundToGame(finishedGame1, 2);
        addCorrectlyAnsweredRoundToGame(finishedGame1, 3);
        addIncorrectlyAnsweredRoundToGame(finishedGame1, 4);
        addIncorrectlyAnsweredRoundToGame(finishedGame1, 5);
        addCorrectlyAnsweredRoundToGame(finishedGame1, 6);

        Game finishedGame2 = new CommonGame("id2", "rock", "hard", 2, 5, 5, 2, LocalDateTime.now(), LocalDateTime.now());
        addCorrectlyAnsweredRoundToGame(finishedGame2, 1);
        addCorrectlyAnsweredRoundToGame(finishedGame2, 2);

        Game finishedGame3 = new CommonGame("id3", "pop", "medium", 1, 2, 2, 1, LocalDateTime.now(), LocalDateTime.now());
        addCorrectlyAnsweredRoundToGame(finishedGame3, 1);

        gameRepository.save(finishedGame1);
        gameRepository.save(finishedGame2);
        gameRepository.save(finishedGame3);

        LifetimeStatistics actualStats = gameRepository.avgStats();

        assertNotNull(actualStats);
        assertEquals(2, actualStats.getAverageScore());
        assertEquals(4, actualStats.getAverageInitialLives());
        assertEquals(3, actualStats.getAverageRoundsPlayed());
        assertEquals("hard", actualStats.getTopDifficulty());
        assertEquals("pop", actualStats.getTopGenre());
    }

    /**
     * Test that avgStats returns null when no games have been saved.
     */
    @Test
    public void avgStatsWithNoGamesPlayedTest() {
        assertNull(gameRepository.avgStats());
    }

    /**
     * Test that a non-empty game repository is empty after clearing.
     */
    @Test
    public void clearTest() {
        Game mockedGame1 = createMockGame();
        Game mockedGame2 = createMockGame();

        gameRepository.save(mockedGame1);
        gameRepository.save(mockedGame2);
        assertTrue(gameRepository.gameExists(mockedGame1));
        assertTrue(gameRepository.gameExists(mockedGame2));

        gameRepository.clear();
        assertFalse(gameRepository.gameExists(mockedGame1));
        assertFalse(gameRepository.gameExists(mockedGame2));
    }


    // ===== HELPERS ===== //

    /**
     * Create a max 3 round game consisting of two correctly answered rounds and one unfinished round.
     */
    private Game createMockGame() {
        LocalDateTime createdAt = LocalDateTime.of(1970, 1, 1, 0, 0);
        Game mockedGame = new CommonGame("id1", "pop", "hard", 3, 3, 3, 2, createdAt, null);
        addCorrectlyAnsweredRoundToGame(mockedGame, 1);
        addCorrectlyAnsweredRoundToGame(mockedGame, 2);
        addUnfinishedRoundToGame(mockedGame, 3);

        return mockedGame;
    }

    private void assertGamesAreDeepCopies(Game game1, Game game2) {
        assertNotNull(game1);
        assertNotNull(game2);

        assertEquals(game1.getID(), game2.getID());
        assertEquals(game1.getDifficulty(), game2.getDifficulty());
        assertEquals(game1.getGenre(), game2.getGenre());
        assertEquals(game1.getInitialLives(), game2.getInitialLives());
        assertEquals(game1.getCurrentLives(), game2.getCurrentLives());
        assertEquals(game1.getMaxRounds(), game2.getMaxRounds());
        assertEquals(game1.getScore(), game2.getScore());
        assertEquals(game1.getCreatedAt(), game2.getCreatedAt());
        assertEquals(game1.getFinishedAt(), game2.getFinishedAt());
        assertEquals(game1.getRoundsPlayed(), game2.getRoundsPlayed());

        List<Round> game1Rounds = game1.getRounds();
        List<Round> game2Rounds = game2.getRounds();
        assertEquals(game1Rounds.size(), game2Rounds.size());

        for (int i = 0; i < game1Rounds.size(); i++) {
            Round game1Round = game1Rounds.get(i);
            Round game2Round = game2Rounds.get(i);
            assertEquals(game1Round.getQuestion(), game2Round.getQuestion());
            assertEquals(game1Round.getCorrectAnswer(), game2Round.getCorrectAnswer());
            assertEquals(game1Round.getUserAnswer(), game2Round.getUserAnswer());

            Song game1Song = game1Round.getSong();
            Song game2Song = game2Round.getSong();
            assertEquals(game1Song.getTitle(), game2Song.getTitle());
            assertEquals(game1Song.getArtist(), game2Song.getArtist());

            PlayableAudio game1Audio = game1Song.getAudio();
            PlayableAudio game2Audio = game2Song.getAudio();
            assertEquals(game1Audio.getPath(), game2Audio.getPath());
        }
    }

    private void addIncorrectlyAnsweredRoundToGame(Game game, int suffix) {
        addRoundToGame(game, 0, suffix);
    }

    private void addCorrectlyAnsweredRoundToGame(Game game, int suffix) {
        addRoundToGame(game, 1, suffix);
    }

    private void addUnfinishedRoundToGame(Game game, int suffix) {
        addRoundToGame(game, 2, suffix);
    }

    private void addRoundToGame(Game game, int userAnswerState, int suffix) {
        PlayableAudio mockedPlayableAudio = new TestPlayableAudio("audio" + suffix + ".mp3");
        Song mockedSong = new CommonSong("song" + suffix, "artist" + suffix, mockedPlayableAudio);
        String correctAnswer = "answer" + suffix;

        String userAnswer;
        if (userAnswerState == 0) {
            userAnswer = "incorrectAnswer" + suffix;
        } else if (userAnswerState == 1) {
            userAnswer = correctAnswer;
        } else {
            userAnswer = null;
        }

        String difficulty = game.getDifficulty().toLowerCase();
        Round mockedRound;
        if (difficulty.equals("easy") || difficulty.equals("medium")) {
            mockedRound = new MultipleChoiceRound(mockedSong, "question" + suffix, correctAnswer, userAnswer, new ArrayList<>());
            // TODO: add multiple choice options
        } else {
            mockedRound = new BasicRound(mockedSong, "question" + suffix, correctAnswer, userAnswer);
        }

        game.setCurrentRound(mockedRound);
    }
}
