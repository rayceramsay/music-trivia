package data_access;

import entity.Game;
import use_case.finish_round.FinishRoundGameDataAccessInterface;
import use_case.submit_answer.SubmitAnswerGameDataAccessInterface;
import entity.CommonGame;
import use_case.create_game.CreateGameDataAccessInterface;

import java.util.HashMap;
import java.util.Map;

public class InMemoryGameDataAccessObject implements CreateGameDataAccessInterface, SubmitAnswerGameDataAccessInterface, FinishRoundGameDataAccessInterface {
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
    public String addGame(String difficulty, String genre, int initialLives, int maxRounds) {
        Game game = new CommonGame(genre, difficulty, maxRounds, initialLives);
        save(game);
        return game.getID();
    }
}
