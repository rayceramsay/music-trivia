package use_case.create_game;

import entity.Game;

/**
 * Data access interface for CreateGame use case
 */
public interface CreateGameDataAccessInterface {
    /**
     * @param gameID GameID
     * @return The Game that correlates to the gameID
     */
    Game getGameByID(String gameID);

    /**
     * @param difficulty difficulty level
     * @param genre      genre of songs played
     * @param lives      number of lives
     * @param rounds     number of rounds
     * @return The String for the Game
     */
    String addGame(String difficulty, String genre, int lives, int rounds);
    void save(Game game);
}
