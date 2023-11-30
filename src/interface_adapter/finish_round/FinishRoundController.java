package interface_adapter.finish_round;

import use_case.finish_round.FinishRoundInputBoundary;
import use_case.finish_round.FinishRoundInputData;

/**
 * Controller for FinishRound interface adapter
 */
public class FinishRoundController {
    private final FinishRoundInputBoundary finishRoundInteractor;

    /**
     * Constructor to initialize objects of FinishRoundController
     *
     * @param finishRoundInteractor FinishRound Input Boundary
     */
    public FinishRoundController(FinishRoundInputBoundary finishRoundInteractor) {
        this.finishRoundInteractor = finishRoundInteractor;
    }

    /**
     * Method to execute the interactor using the given input data
     *
     * @param gameId ID of current game
     */
    public void execute(String gameId) {
        FinishRoundInputData inputData = new FinishRoundInputData(gameId);
        finishRoundInteractor.execute(inputData);
    }
}
