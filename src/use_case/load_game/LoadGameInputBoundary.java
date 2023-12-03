package use_case.load_game;

/**
 * Input Boundary interface for LoadGame use case
 */
public interface LoadGameInputBoundary {
    /**
     * Get the specific game that needs to be loaded by using its gameId.
     * Prepare the view in the presenter using the output data of the game.
     * This includes prompt of the current round, genre, initial lives, current amount of lives, max amount of rounds and amount of rounds played so far.
     *
     * @param inputData includes gameId
     */
    void execute(LoadGameInputData inputData);
}
