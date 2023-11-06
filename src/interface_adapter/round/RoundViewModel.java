package interface_adapter.round;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;

public class RoundViewModel extends ViewModel {
    public String TITLE_LABEL = "What song is this?";
    public RoundViewModel() { super("round view"); }
    @Override
    public void firePropertyChanged() {
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {

    }
}
