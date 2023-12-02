package use_case.exit_round;

/**
 * Output boundary for CreateGame use case
 */
public interface ExitRoundOutputBoundary {

    /**
     * Update the toggle audio image state to be the play image, clear the user answer state, and go to the main menu.
     */
    void prepareView();
}
