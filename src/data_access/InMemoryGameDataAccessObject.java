package data_access;

import entity.*;
import use_case.finish_round.FinishRoundGameDataAccessInterface;
import use_case.get_loadable_games.GetLoadableGamesGameDataAccessInterface;
import use_case.load_game.LoadGameGameDataAccessInterface;
import use_case.statistics.StatisticsDataAccessInterface;
import use_case.submit_answer.SubmitAnswerGameDataAccessInterface;
import use_case.toggle_audio.ToggleAudioGameDataAccessInterface;
import use_case.create_game.CreateGameDataAccessInterface;

import java.util.*;

public class InMemoryGameDataAccessObject implements SubmitAnswerGameDataAccessInterface, FinishRoundGameDataAccessInterface,
        CreateGameDataAccessInterface, GetLoadableGamesGameDataAccessInterface, LoadGameGameDataAccessInterface,
        StatisticsDataAccessInterface, ToggleAudioGameDataAccessInterface {

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

    public LifetimeStatistics avgStats() {
        int gamesPlayed = games.size();

        if (gamesPlayed == 0) {
            return null;
        }

        Map<String, Integer> difficultyCountMap = new HashMap<>();
        Map<String, Integer> genreCountMap = new HashMap<>();
        int scoreSum = 0;
        int sumInitialLives = 0;
        int roundsPlayed = 0;

        for (Game game : games.values()) {
            String difficulty = game.getDifficulty().toLowerCase();
            if (difficultyCountMap.containsKey(difficulty)) {
                difficultyCountMap.put(difficulty, difficultyCountMap.get(difficulty) + 1);
            } else {
                difficultyCountMap.put(difficulty, 1);
            }

            String genre = game.getGenre().toLowerCase();
            if (genreCountMap.containsKey(genre)) {
                genreCountMap.put(genre, genreCountMap.get(genre) + 1);
            } else {
                genreCountMap.put(genre, 1);
            }

            scoreSum += game.getScore();
            sumInitialLives += game.getInitialLives();
            roundsPlayed += game.getRoundsPlayed();
        }

        String mostCommonDifficulty = Collections.max(difficultyCountMap.entrySet(), Map.Entry.comparingByValue()).getKey();
        String mostCommonGenre = Collections.max(genreCountMap.entrySet(), Map.Entry.comparingByValue()).getKey();

        return new CommonLifetimeStatistics(
            Math.round((float) scoreSum / gamesPlayed),
            Math.round((float) sumInitialLives / gamesPlayed),
            Math.round((float) roundsPlayed / gamesPlayed),
            mostCommonDifficulty,
            mostCommonGenre);
    }

    public boolean gameExists(Game game) {
        return games.containsKey(game.getID());
    }

    public void clear() {
        games.clear();
    }
}