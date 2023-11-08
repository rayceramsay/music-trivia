package data_access;

import entity.Game;
import use_case.submit_answer.SubmitAnswerDataAccessInterface;

import java.util.HashMap;
import java.util.Map;

public class InMemoryGameDataAccessObject implements SubmitAnswerDataAccessInterface {
    private final Map<String, Game> games = new HashMap<>();

    @Override
    public Game getGameByID(String gameId) {
        return games.get(gameId);
    }

    @Override
    public void save(Game game) { games.put(game.getID(), game); }
}
