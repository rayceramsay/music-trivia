package interface_adapter.round;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class RoundViewModel extends ViewModel {
    public String TITLE_LABEL = "What song is this?";

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);
    private RoundState state = new RoundState();

    public RoundViewModel(String viewName) {
        super(viewName);
    }

    @Override
    public void firePropertyChanged() {
        support.firePropertyChange("roundState", null, this.state);
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public RoundState getState() {
        return state;
    }

    public void setState(RoundState state) {
        this.state = state;
    }
}
