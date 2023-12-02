package interface_adapter.exit_round;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;

/**
 * Extension of ViewModel for ExitRound interface adapter
 */
public class ExitRoundViewModel extends ViewModel {

    public ExitRoundViewModel(String viewName) {
        super(viewName);
    }

    @Override
    public void firePropertyChanged() {
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
    }
}
