package interface_adapter.game_settings;

import interface_adapter.ViewModel;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * Extension of ViewModel for GameSettings interface adapter
 */
public class GameSettingsViewModel extends ViewModel {

    public final String DIFFICULTY_SELECTOR_LABEL = "Difficulty: ";
    public final String GENRE_SELECTOR_LABEL = "Genre: ";
    public final String LIVES_SPINNER_LABEL = "Lives: ";
    public final String ROUNDS_SPINNER_LABEL = "Rounds: ";

    public final String PLAY_GAME_LABEL = "PLAY GAME";
    public final String BACK_LABEL = "BACK";

    private GameSettingsState state = new GameSettingsState();
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    /**
     * Constructor to initialize objects of GameSettingsViewModel
     *
     * @param viewName name of View
     */
    public GameSettingsViewModel(String viewName) {
        super(viewName);
    }

    @Override
    public void firePropertyChanged() {
        support.firePropertyChange("gameSettingsState", null, this.state);
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    /**
     * @return GameSettingsState
     */
    public GameSettingsState getState() {
        return state;
    }

    /**
     * @param state GameSettingsState
     */
    public void setState(GameSettingsState state) {
        this.state = state;
    }
}
