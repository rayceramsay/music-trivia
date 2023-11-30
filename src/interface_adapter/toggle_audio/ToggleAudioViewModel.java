package interface_adapter.toggle_audio;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * Extension of ViewModel for toggle audio interface adapter
 */
public class ToggleAudioViewModel extends ViewModel {

    public static final String STATE_PROPERTY = "toggleAudioState";
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);
    private ToggleAudioState state = new ToggleAudioState();

    /**
     * Constructor to initialize objects of ToggleAudioViewModel
     *
     * @param viewName name of view
     */
    public ToggleAudioViewModel(String viewName) {
        super("round");
    }

    @Override
    public void firePropertyChanged() {
        support.firePropertyChange(STATE_PROPERTY, null, this.state);
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public ToggleAudioState getState() {
        return state;
    }

    public void setState(ToggleAudioState state) {
        this.state = state;
    }
}
