package interface_adapter.exit_round;

import use_case.exit_round.ExitRoundInputBoundary;
import use_case.exit_round.ExitRoundInputData;

public class ExitRoundController {

    private final ExitRoundInputBoundary exitRoundInteractor;

    /**
     * Constructor to initialize objects of CreateGameController
     *
     * @param exitRoundInteractor ExitRound input boundary
     */
    public ExitRoundController(ExitRoundInputBoundary exitRoundInteractor) {
        this.exitRoundInteractor = exitRoundInteractor;
    }

    /**
     * Method to execute the interactor using the given input data
     *
     * @param gameID ID of current game
     */
    public void execute(String gameID) {
        ExitRoundInputData inputData = new ExitRoundInputData(gameID);
        exitRoundInteractor.execute(inputData);
    }
}
