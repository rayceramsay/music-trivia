package use_case.exit_round;

public interface ExitRoundOutputBoundary {

    /**
     * Update the toggle audio image state to be the play image, clear the user answer state, and go to the main menu.
     */
    void prepareView();
}
