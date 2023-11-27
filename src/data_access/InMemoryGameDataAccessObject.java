package data_access;

import entity.Game;
import use_case.finish_round.FinishRoundGameDataAccessInterface;
import use_case.statistics.StatisticsDataAccessInterface;
import use_case.submit_answer.SubmitAnswerGameDataAccessInterface;

import java.util.*;

import static java.util.Collections.max;

public class InMemoryGameDataAccessObject implements SubmitAnswerGameDataAccessInterface, FinishRoundGameDataAccessInterface, StatisticsDataAccessInterface {
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
    public HashMap<String, Object> avgStats() {
        int gamesPlayed = 0;
        int scoreSum = 0;
        int sumInitialLives = 0;
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
        HashMap<String, Object> allStats = new HashMap<>();
        allStats.put("Average Score", scoreSum/gamesPlayed);
        allStats.put("Average Initial Lives", sumInitialLives/gamesPlayed);
        allStats.put("Most Common Genre", mostCommonGenre);
        allStats.put("Most Common Game Difficulty", mostCommonDifficulty);
        return allStats;
    }
}
