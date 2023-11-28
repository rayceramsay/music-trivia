package use_case.create_game;

import entity.Game;

public interface CreateGameDataAccessInterface {
    Game getGameByID(String gameID);

    String addGame(String difficulty, String genre, int lives, int rounds);
}
