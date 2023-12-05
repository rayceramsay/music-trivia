package use_case.create_game;

/**
 * Output boundary for CreateGame use case
 */
public interface CreateGameOutputBoundary {
    /**
     * Prepare the View for the first round of the game.
     *
     * @param createGameOutputData includes GameId, genre, difficulty, rounds, and lives
     */
    void prepareFirstRoundView(CreateGameOutputData createGameOutputData);
}
