package interface_adapter.get_loadable_games;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * Extension of ViewModel for GetLoadableGames interface adapter
 */
public class GetLoadableGamesViewModel extends ViewModel {

    public final static String STATE_PROPERTY = "getLoadableGamesState";

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);
    private final GetLoadableGamesState state = new GetLoadableGamesState();

    public GetLoadableGamesViewModel(String viewName) {
        super(viewName);
    }

    @Override
    public void firePropertyChanged() {
        support.firePropertyChange(STATE_PROPERTY, null, state);
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    /**
     * @return state of GetLoadableGames
     */
    public GetLoadableGamesState getState() {
        return state;
    }
}
