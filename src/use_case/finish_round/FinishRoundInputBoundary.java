package use_case.finish_round;

/**
 * Input boundary interface for Finish Round use case
 */
public interface FinishRoundInputBoundary {
    /**
     * If the game is over, update the time it ended at, and save the game.
     * Update the score in the output data and prepare the game over view in the presenter using the output data.
     * If the game is not over, create the next round using the game difficulty parameter, and set the current round to next round.
     * Also create in instance of FinishRoundOutputData and set the genre, lives and round number.
     *
     * @param inputData gameID
     */
    void execute(FinishRoundInputData inputData);
}
