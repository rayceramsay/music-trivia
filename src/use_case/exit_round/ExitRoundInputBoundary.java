package use_case.exit_round;

public interface ExitRoundInputBoundary {

    /**
     * Execute the exit round business logic, i.e. stop the round's playing audio.
     *
     * @param inputData a DTO holding the game ID of the round to exit
     */
    void execute(ExitRoundInputData inputData);
}
