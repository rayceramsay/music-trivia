package interface_adapter;

import java.beans.PropertyChangeListener;

public abstract class ViewModel {
    private final String viewName;

    /**
     * Constructor to initialize objects of ViewModel
     *
     * @param viewName name of view
     */
    public ViewModel(String viewName) {
        this.viewName = viewName;
    }

    public String getViewName() {
        return this.viewName;
    }

    public abstract void firePropertyChanged();

    public abstract void addPropertyChangeListener(PropertyChangeListener listener);
}
