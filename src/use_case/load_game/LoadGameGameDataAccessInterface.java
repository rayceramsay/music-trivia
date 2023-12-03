package use_case.load_game;

import entity.Game;

/**
 * Data access interface for Load Game use case
 */
public interface LoadGameGameDataAccessInterface {
    /**
     * @param gameID Gets a specific game via its gameID.
     * @return A game
     */
    Game getGameByID(String gameID);

    /**
     * @param game A game
     */
    void save(Game game);
}
