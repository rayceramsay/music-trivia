package interface_adapter.menu;

import interface_adapter.ViewModel;
import interface_adapter.menu.MenuState;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class MenuViewModel extends ViewModel {

    public final String NEWGAME_BUTTON_LABEL = "NEW GAME";

    public final String LOADGAME_BUTTON_LABEL = "LOAD GAME";

    public final String CAREERSTATS_BUTTON_LABEL = "CAREER STATS";

    private MenuState state = new MenuState();

    public MenuViewModel() {
        super("menu view");
    }

    @Override
    public void firePropertyChanged() {

    }

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.firePropertyChange("state", null, this.state);
    }

    public MenuState getState() {
        return state;
    }
}
