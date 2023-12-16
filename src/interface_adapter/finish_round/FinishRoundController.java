package interface_adapter.finish_round;

import use_case.finish_round.FinishRoundInputBoundary;
import use_case.finish_round.FinishRoundInputData;

public class FinishRoundController {

    private final FinishRoundInputBoundary finishRoundInteractor;

    public FinishRoundController(FinishRoundInputBoundary finishRoundInteractor) {
        this.finishRoundInteractor = finishRoundInteractor;
    }

    public void execute(String gameId) {
        FinishRoundInputData inputData = new FinishRoundInputData(gameId);
        finishRoundInteractor.execute(inputData);
    }
}
