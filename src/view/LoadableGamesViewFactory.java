package view;

import interface_adapter.ViewManagerModel;
import interface_adapter.get_loadable_games.GetLoadableGamesViewModel;

public class LoadableGamesViewFactory {

    private LoadableGamesViewFactory() {}

    public static LoadableGamesView create(ViewManagerModel viewManagerModel,
                                           GetLoadableGamesViewModel getLoadableGamesViewModel) {

        return new LoadableGamesView(viewManagerModel, getLoadableGamesViewModel);
    }
}
