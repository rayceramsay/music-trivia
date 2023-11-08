package data_access;

import entity.Game;
import use_case.submit_answer.SubmitAnswerGameDataAccessInterface;

import java.util.HashMap;
import java.util.Map;

public class InMemoryGameDataAccessObject implements SubmitAnswerGameDataAccessInterface {
    private final Map<String, Game> games = new HashMap<>();  // maps gameID to game object

    @Override
    public Game getGameByID(String gameID) {
        return games.get(gameID);
    }

    @Override
    public void save(Game game) {
        games.put(game.getID(), game);
    }
}
