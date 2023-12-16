package interface_adapter.finish_round;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class FinishRoundViewModel extends ViewModel {

    public static final String STATE_PROPERTY = "finishRound";

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public FinishRoundViewModel(String viewName) {
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
