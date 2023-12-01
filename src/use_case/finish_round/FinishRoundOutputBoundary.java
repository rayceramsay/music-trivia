package use_case.finish_round;

/**
 * Output boundary for finish round use case
 */
public interface FinishRoundOutputBoundary {
    /**
     * This method will set the state using gameOverViewModel, provided the final round of the game was just played.
     * It will also set the final score of the game
     *
     * @param outputData includes score, round number, genre, and lives
     */
    void prepareGameOverView(FinishRoundOutputData outputData);

    /**
     * This method will set the state using roundViewModel, provided it is not the last round of the game.
     * It will then add one to the current round number signifying that the previous one has just been finished.
     * And set the genre, amount of lives left, as well as reset the user answer to empty string for the next round
     *
     * @param outputData includes score, round number, genre, and lives
     */
    void prepareNextRoundView(FinishRoundOutputData outputData);
}
