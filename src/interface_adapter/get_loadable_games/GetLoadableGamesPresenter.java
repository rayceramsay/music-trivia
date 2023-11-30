package interface_adapter.get_loadable_games;

import interface_adapter.ViewManagerModel;
import use_case.get_loadable_games.GetLoadableGamesOutputBoundary;
import use_case.get_loadable_games.GetLoadableGamesOutputData;
import use_case.get_loadable_games.GetLoadableGamesOutputDataItem;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of GetLoadableGamesOutputBoundary interface
 */
public class GetLoadableGamesPresenter implements GetLoadableGamesOutputBoundary {

    private final GetLoadableGamesViewModel getLoadableGamesViewModel;
    private final ViewManagerModel viewManagerModel;

    /**
     * Constructor to initialize objects of GetLoadableGamesPresenter
     *
     * @param getLoadableGamesViewModel View Model for getLoadableGames
     * @param viewManagerModel          View Manager Model
     */
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
                    outputDataItem.getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));

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
