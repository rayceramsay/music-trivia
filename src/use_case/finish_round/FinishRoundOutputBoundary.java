package use_case.finish_round;

public interface FinishRoundOutputBoundary {
    void prepareGameOverView(FinishRoundOutputData outputData);
    void prepareNextRoundView(FinishRoundOutputData outputData);
}
