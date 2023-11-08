package interface_adapter.submit_answer;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class SubmitAnswerViewModel extends ViewModel {
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);
    private SubmitAnswerState state = new SubmitAnswerState();

    public SubmitAnswerViewModel() { super("round"); }

    @Override
    public void firePropertyChanged() {
        support.firePropertyChange("submitAnswerState", null, this.state);
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public SubmitAnswerState getState() {
        return state;
    }

    public void setState(SubmitAnswerState state) {
        this.state = state;
    }
}
