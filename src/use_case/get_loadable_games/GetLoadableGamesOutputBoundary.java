package use_case.get_loadable_games;

public interface GetLoadableGamesOutputBoundary {
    /**
     * Provided loadable games exist in list of loadable games output data, prepare games from the list of loadable games by adding their data (gameId, genre, difficulty, initial lives, current lives, max rounds) to gamesData.
     * Update view accordingly.
     * @param outputData List of loadable games
     */
    void prepareGamesExistView(GetLoadableGamesOutputData outputData);

    /**
     * Provided no loadable games exist in list of loadable games output data, set loadable games to be an empty ArrayList and set error message.
     * Update view accordingly.
     * @param message Error message
     */
    void prepareNoGamesExistView(String message);
}
