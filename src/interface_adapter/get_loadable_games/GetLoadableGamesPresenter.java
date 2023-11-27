package interface_adapter.get_loadable_games;

import interface_adapter.ViewManagerModel;
import use_case.get_loadable_games.GetLoadableGamesOutputBoundary;
import use_case.get_loadable_games.GetLoadableGamesOutputData;
import use_case.get_loadable_games.GetLoadableGamesOutputDataItem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GetLoadableGamesPresenter implements GetLoadableGamesOutputBoundary {

    private final GetLoadableGamesViewModel getLoadableGamesViewModel;
    private final ViewManagerModel viewManagerModel;

    public GetLoadableGamesPresenter(GetLoadableGamesViewModel getLoadableGamesViewModel, ViewManagerModel viewManagerModel) {
        this.getLoadableGamesViewModel = getLoadableGamesViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void prepareGamesExistView(GetLoadableGamesOutputData outputData) {
        List<Map<String, String>> gamesData = new ArrayList<>();
        GetLoadableGamesState loadableGamesState = getLoadableGamesViewModel.getState();

        for (GetLoadableGamesOutputDataItem outputDataItem : outputData.getGetLoadableGamesOutputDataItems()) {
            Map<String, String> gameData = new HashMap<>();

            gameData.put("ID", outputDataItem.getGameID());
            gameData.put("difficulty", outputDataItem.getDifficulty());
            gameData.put("genre", outputDataItem.getGenre());
            gameData.put("initialLives", String.valueOf(outputDataItem.getInitialLives()));
            gameData.put("currentLives", String.valueOf(outputDataItem.getCurrentLives()));
            gameData.put("maxRounds", String.valueOf(outputDataItem.getMaxRounds()));
            gameData.put("currentRoundNumber", String.valueOf(outputDataItem.getCurrentRoundNumber()));
            gameData.put("createdAt", outputDataItem.getCreatedAt());

            gamesData.add(gameData);
        }

        loadableGamesState.setGamesData(gamesData);
        loadableGamesState.setErrorMessage("");

        viewManagerModel.setActiveView(getLoadableGamesViewModel.getViewName());
        getLoadableGamesViewModel.firePropertyChanged();
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareNoGamesExistView(String message) {
        GetLoadableGamesState loadableGamesState = getLoadableGamesViewModel.getState();

        loadableGamesState.setErrorMessage(message);
        loadableGamesState.setGamesData(new ArrayList<>());

        viewManagerModel.setActiveView(getLoadableGamesViewModel.getViewName());
        getLoadableGamesViewModel.firePropertyChanged();
        viewManagerModel.firePropertyChanged();
    }
}
