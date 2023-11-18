package interface_adapter.get_loadable_games;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;

public class GetLoadableGamesViewModel extends ViewModel {
    public GetLoadableGamesViewModel() {
        super("unfinishedGames");
    }

    @Override
    public void firePropertyChanged() {

    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {

    }
}
