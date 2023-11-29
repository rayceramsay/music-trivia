package use_case.get_loadable_games;

import entity.Game;

import java.util.List;

public interface GetLoadableGamesGameDataAccessInterface {
    List<Game> getLoadableGames();
    void save(Game game);
}
