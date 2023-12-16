package interface_adapter.load_game;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class LoadGameViewModel extends ViewModel {

    public static final String STATE_PROPERTY = "loadGame";

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public LoadGameViewModel(String viewName) {
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
