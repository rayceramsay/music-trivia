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
        List<GetLoadableGamesStateItem> gamesData = new ArrayList<>();
        GetLoadableGamesState loadableGamesState = getLoadableGamesViewModel.getState();

        for (GetLoadableGamesOutputDataItem outputDataItem : outputData.getGetLoadableGamesOutputDataItems()) {
            GetLoadableGamesStateItem gameData = new GetLoadableGamesStateItem(
                    outputDataItem.getGameID(),
                    outputDataItem.getGenre(),
                    outputDataItem.getDifficulty(),
                    String.valueOf(outputDataItem.getInitialLives()),
                    String.valueOf(outputDataItem.getCurrentLives()),
                    String.valueOf(outputDataItem.getMaxRounds()),
                    String.valueOf(outputDataItem.getCurrentRoundNumber()),
                    outputDataItem.getCreatedAt());

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
