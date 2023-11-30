package interface_adapter.game_over;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class GameOverViewModel extends ViewModel {

    public static final String TITLE_LABEL = "GAME OVER!";
    public static final String MAIN_MENU_LABEL = "Go to main menu";

    private GameOverState state = new GameOverState();

    public GameOverViewModel(String viewName) {
        super(viewName);
    }

    public void setState(GameOverState state) {
        this.state = state;
    }

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public GameOverState getState() {
        return state;
    }
}
