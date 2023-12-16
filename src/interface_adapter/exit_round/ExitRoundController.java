package interface_adapter.exit_round;

import use_case.exit_round.ExitRoundInputBoundary;
import use_case.exit_round.ExitRoundInputData;

public class ExitRoundController {

    private final ExitRoundInputBoundary exitRoundInteractor;

    public ExitRoundController(ExitRoundInputBoundary exitRoundInteractor) {
        this.exitRoundInteractor = exitRoundInteractor;
    }

    public void execute(String gameID) {
        ExitRoundInputData inputData = new ExitRoundInputData(gameID);
        exitRoundInteractor.execute(inputData);
    }
}
