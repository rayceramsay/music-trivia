package interface_adapter.create_game;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
/**
 * Extension of ViewModel for CreateGame interface adapter
 */
public class CreateGameViewModel extends ViewModel {

    public static final String STATE_PROPERTY = "createGame";
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public CreateGameViewModel(String viewName) {
        super(viewName);
    }

    @Override
    public void firePropertyChanged() {
        support.firePropertyChange(STATE_PROPERTY, null, null);
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }
}
