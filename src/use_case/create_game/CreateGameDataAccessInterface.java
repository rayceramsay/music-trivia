package use_case.create_game;

import entity.Game;

public interface CreateGameDataAccessInterface {
    void save(Game game);
}
