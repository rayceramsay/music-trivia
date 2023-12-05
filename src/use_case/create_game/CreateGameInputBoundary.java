package use_case.create_game;

/**
 * Input Boundary interface for CreateGame use case
 */
public interface CreateGameInputBoundary {
    /**
     * Set GameID with the parameters of the input data.
     * Create instance of output data and set its attributes with the gameID, genre, difficulty, rounds, and lives.
     * PrepareFirstRoundView using those attributes of output data.
     *
     * @param inputData includes genre of songs, difficulty level, amount of lives, and total rounds
     */
    void execute(CreateGameInputData inputData);
}
