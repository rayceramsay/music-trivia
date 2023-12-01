package use_case.load_game;

/**
 * Output boundary for load game use case
 */
public interface LoadGameOutputBoundary {
    /**
     * Prepares View for loading a game by setting all given attributes from the output data.
     * This data includes, GameId, PromptText, CurrentRoundNumber, MaxRounds, CurrentLives, InitialLives, Genre, UserAnswer.
     * Updates the round view model with the new round state.
     *
     * @param outputData GameID, question, genre, initial lives, current lives, max rounds, current round number
     */
    void prepareView(LoadGameOutputData outputData);
}
