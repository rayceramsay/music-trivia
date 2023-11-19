package interface_adapter.get_loadable_games;

import interface_adapter.ViewManagerModel;
import use_case.get_loadable_games.GetLoadableGamesOutputBoundary;
import use_case.get_loadable_games.GetLoadableGamesOutputData;

import java.util.ArrayList;

public class GetLoadableGamesPresenter implements GetLoadableGamesOutputBoundary {

    private final GetLoadableGamesViewModel getLoadableGamesViewModel;
    private final ViewManagerModel viewManagerModel;

    public GetLoadableGamesPresenter(GetLoadableGamesViewModel getLoadableGamesViewModel, ViewManagerModel viewManagerModel) {
        this.getLoadableGamesViewModel = getLoadableGamesViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void prepareGamesExistView(GetLoadableGamesOutputData outputData) {
        GetLoadableGamesState loadableGamesState = getLoadableGamesViewModel.getState();

        loadableGamesState.setGamesData(outputData.getLoadableGamesData());
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
