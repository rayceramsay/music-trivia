package use_case.get_loadable_games;

/**
 * Input Boundary interface for GetLoadableGames use case
 */
public interface GetLoadableGamesInputBoundary {
    /**
     * Create an ArrayList of loadable games.
     * For each game in this list, add its game data.
     * If this list ends up being empty, call the prepareNoGamesExistView method of the presenter with a message.
     * If this list has games, call the prepareGamesExistView method of the presenter with the output data.
     */
    void execute();
}
