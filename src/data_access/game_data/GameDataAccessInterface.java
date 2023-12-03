package data_access.game_data;

import entity.Game;
import entity.LifetimeStatistics;

import java.util.List;

/**
 * Interface for Game Data
 */
public interface GameDataAccessInterface {
    /**
     * @param gameID GameID
     * @return A game
     */
    Game getGameByID(String gameID);

    /**
     * @param game A game
     */
    void save(Game game);

    /**
     * @return A list of games
     */
    List<Game> getLoadableGames();

    /**
     * @return All statistics
     */
    LifetimeStatistics avgStats();

    /**
     * @param game A game
     * @return whether that game exists
     */
    boolean gameExists(Game game);

    void clear();
}
