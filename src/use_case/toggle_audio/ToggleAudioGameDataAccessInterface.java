package use_case.toggle_audio;

import entity.Game;

/**
 * Data access interface for ToggleAudio use case
 */
public interface ToggleAudioGameDataAccessInterface {
    /**
     * Gets a specific game via its gameID.
     *
     * @param gameId GameID
     * @return A game
     */
    Game getGameByID(String gameId);

    /**
     * Saves a specific game within a HashMap of 'games'.
     * Adds functionality of allowing a user to return to an incomplete game and continue where they left off
     *
     * @param game A game
     */
    void save(Game game);
}