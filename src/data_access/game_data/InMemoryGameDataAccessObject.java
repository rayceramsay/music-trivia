package data_access.game_data;

import entity.CommonGame;
import entity.CommonLifetimeStatistics;
import entity.Game;
import entity.LifetimeStatistics;

import java.util.*;

/**
 * Implementation of the Game DAO using in memory storage.
 * The Game DAO class includes all interfaces that define methods for accessing the required data for the game
 */
public class InMemoryGameDataAccessObject implements GameDataAccessInterface {

    private final Map<String, Game> games = new HashMap<>();  // maps gameID to game object

    @Override
    public Game getGameByID(String gameID) {
        return games.get(gameID);
    }

    @Override
    public void save(Game game) {
        games.put(game.getID(), game);
    }

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

    @Override
    public String addGame(String difficulty, String genre, int initialLives, int maxRounds) {
        Game game = new CommonGame(genre, difficulty, maxRounds, initialLives);
        save(game);

        return game.getID();
    }

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
