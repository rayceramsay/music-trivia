package interface_adapter.game_over;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * Implementation of ViewModel for GameOver interface adapter
 */
public class GameOverViewModel extends ViewModel {

    public static final String TITLE_LABEL = "GAME OVER!";
    public static final String MAIN_MENU_LABEL = "Go to main menu";

    private GameOverState state = new GameOverState();

    /**
     * Constructor to initialize objects of GameOverViewModel
     *
     * @param viewName Name of required View
     */
    public GameOverViewModel(String viewName) {
        super(viewName);
    }

    /**
     * @param state GameOverState
     */
    public void setState(GameOverState state) {
        this.state = state;
    }

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    /**
     * Method to change state
     */
    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this.state);
    }

    /**
     * @param listener PropertyChangeListener
     */
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    /**
     * @return state for GameOver
     */
    public GameOverState getState() {
        return state;
    }
}
