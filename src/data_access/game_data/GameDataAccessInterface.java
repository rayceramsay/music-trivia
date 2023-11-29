package data_access.game_data;

import entity.Game;
import entity.LifetimeStatistics;

import java.util.List;

public interface GameDataAccessInterface {
    Game getGameByID(String gameID);
    String addGame(String difficulty, String genre, int lives, int rounds);
    void save(Game game);
    List<Game> getLoadableGames();
    LifetimeStatistics avgStats();
}
