package use_case.exit_round;

import entity.Game;

public interface ExitRoundGameDataAccessInterface {

    /**
     * Return the game with ID of gameID.
     *
     * @param gameID the ID of the desired game
     * @return the game whose ID matches gameID
     */
    Game getGameByID(String gameID);

    /**
     * Save the game to persistence.
     *
     * @param game the game to save
     */
    void save(Game game);
}
