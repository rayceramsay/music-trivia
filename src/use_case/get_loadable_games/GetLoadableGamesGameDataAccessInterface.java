package use_case.get_loadable_games;

import entity.Game;

import java.util.List;

/**
 * Data access interface for Get Loadable Games use case
 */
public interface GetLoadableGamesGameDataAccessInterface {
    /**
     * Adds incomplete games to a list of possible loadable games
     * Adds functionality of being able to load a previously played game which is still incomplete
     *
     * @return A list of games
     */
    List<Game> getLoadableGames();

    /**
     * Saves a specific game within a HashMap of 'games'.
     * Adds functionality of allowing a user to return to an incomplete game and continue where they left off.
     *
     * @param game A game
     */
    void save(Game game);
}
