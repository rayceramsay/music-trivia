package use_case.get_loadable_games;

public interface GetLoadableGamesOutputBoundary {
    void prepareGamesExistView(GetLoadableGamesOutputData outputData);
    void prepareNoGamesExistView(String message);
}
