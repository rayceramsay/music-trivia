package use_case.load_game;

import entity.Game;

public interface LoadGameGameDataAccessInterface {
    Game getGameByID(String gameID);
    void save(Game game);
}
