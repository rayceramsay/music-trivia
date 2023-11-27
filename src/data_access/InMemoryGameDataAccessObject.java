package data_access;

import entity.Game;
import use_case.finish_round.FinishRoundGameDataAccessInterface;
import use_case.get_loadable_games.GetLoadableGamesGameDataAccessInterface;
import use_case.load_game.LoadGameGameDataAccessInterface;
import use_case.submit_answer.SubmitAnswerGameDataAccessInterface;

import java.util.*;

public class InMemoryGameDataAccessObject implements SubmitAnswerGameDataAccessInterface, FinishRoundGameDataAccessInterface,
        GetLoadableGamesGameDataAccessInterface, LoadGameGameDataAccessInterface {
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
        for (Game game: games.values()) {
            if (!game.isGameOver()) {
                loadableGames.add(game);
            }
        }

        loadableGames.sort(Comparator.comparing(Game::getCreatedAt, Comparator.reverseOrder()));

        return loadableGames;
    }
}
