package data_access;

import entity.CommonGame;
import entity.CommonLifetimeStatistics;
import entity.Game;
import entity.LifetimeStatistics;
import use_case.create_game.CreateGameDataAccessInterface;
import use_case.finish_round.FinishRoundGameDataAccessInterface;
import use_case.get_loadable_games.GetLoadableGamesGameDataAccessInterface;
import use_case.load_game.LoadGameGameDataAccessInterface;
import use_case.statistics.StatisticsDataAccessInterface;
import use_case.submit_answer.SubmitAnswerGameDataAccessInterface;
import use_case.toggle_audio.ToggleAudioGameDataAccessInterface;

import java.util.*;

/**
 * Implementation of the Game DAO using in memory storage.
 * The Game DAO class includes all interfaces that define methods for accessing the required data for the game
 */
public class InMemoryGameDataAccessObject implements SubmitAnswerGameDataAccessInterface, FinishRoundGameDataAccessInterface,
        CreateGameDataAccessInterface, GetLoadableGamesGameDataAccessInterface, LoadGameGameDataAccessInterface,
        StatisticsDataAccessInterface, ToggleAudioGameDataAccessInterface {

    private final Map<String, Game> games = new HashMap<>();  // maps gameID to game object

    /**
     * Gets a specific game via its gameID.
     *
     * @param gameID
     * @return Game
     */
    @Override
    public Game getGameByID(String gameID) {
        return games.get(gameID);
    }

    /**
     * Saves a specific game within a HashMap of 'games'.
     * Adds functionality of allowing a user to return to an incomplete game and continue where they left off.
     *
     * @param game
     */
    @Override
    public void save(Game game) {
        games.put(game.getID(), game);
    }

    /**
     * Adds incomplete games to a list of possible loadable games
     * Adds functionality of being able to load a previously played game which is still incomplete
     *
     * @return List of Games
     */
    @Override
    public List<Game> getLoadableGames() {
        List<Game> loadableGames = new ArrayList<>();
        for (Game game : games.values()) {
            if (!game.isGameOver()) {
                loadableGames.add(game);
            }
        }

        loadableGames.sort(Comparator.comparing(Game::getCreatedAt, Comparator.reverseOrder()));

        return loadableGames;
    }

    /**
     * Creates game with set difficulty, genre, initial lives, and max rounds
     *
     * @param difficulty
     * @param genre
     * @param initialLives
     * @param maxRounds
     * @return String
     */
    @Override
    public String addGame(String difficulty, String genre, int initialLives, int maxRounds) {
        Game game = new CommonGame(genre, difficulty, maxRounds, initialLives);
        save(game);

        return game.getID();
    }

    /**
     * Outputs statistics:
     * Average score,
     * Average initial lives,
     * Average rounds played,
     * Most common difficulty level,
     * Most common genre,
     *
     * @return LifetimeStatistics
     */
    @Override
    public LifetimeStatistics avgStats() {
        int gamesPlayed = 0;
        int scoreSum = 0;
        int sumInitialLives = 0;
        int roundsPlayed = 0;
        int[] difficultiesCount = new int[3]; // To track Easy, Medium, Hard count: index 0 -> Easy, 1 -> Medium, 2 -> Hard
        int[] genresCount = new int[3];
        for (Game game : games.values()) {
            switch (game.getDifficulty()) {
                case "Easy" -> difficultiesCount[0]++;
                case "Medium" -> difficultiesCount[1]++;
                default -> difficultiesCount[2]++;
            }
            switch (game.getGenre()) {
                case "Rock" -> genresCount[0]++;
                case "Pop" -> genresCount[1]++;
                default -> genresCount[2]++;
            }
            gamesPlayed += 1;
            scoreSum += game.getScore();
            sumInitialLives += game.getInitialLives();
            roundsPlayed += game.getRoundsPlayed();
        }

        String[] difficultyLevels = {"Easy", "Medium", "Hard"};
        int maxCountIndex = 0;

        for (int i = 1; i < difficultiesCount.length; i++) {
            if (difficultiesCount[i] > difficultiesCount[maxCountIndex]) {
                maxCountIndex = i;
            }
        }

        String[] genres = {"Rock", "Pop", "Hip-Hop"};
        int maxCountIndex2 = 0;

        for (int i = 1; i < genresCount.length; i++) {
            if (genresCount[i] > genresCount[maxCountIndex2]) {
                maxCountIndex2 = i;
            }
        }

        String mostCommonDifficulty = difficultyLevels[maxCountIndex];
        String mostCommonGenre = genres[maxCountIndex2];
        if (gamesPlayed == 0) {
            return null;
        } else {
            return new CommonLifetimeStatistics(
                    scoreSum / gamesPlayed,
                    sumInitialLives / gamesPlayed,
                    roundsPlayed / gamesPlayed,
                    mostCommonDifficulty,
                    mostCommonGenre);
        }
    }
}