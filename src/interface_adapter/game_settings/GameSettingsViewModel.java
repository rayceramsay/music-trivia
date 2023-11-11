package interface_adapter.game_settings;

import interface_adapter.ViewModel;
import interface_adapter.menu.MenuState;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class GameSettingsViewModel extends ViewModel {

    public final String DIFFICULTY_SELECTOR_LABEL = "Difficulty: ";
    public final String GENRE_SELECTOR_LABEL = "Genre: ";
    public final String LIVES_SPINNER_LABEL = "Lives: ";
    public final String ROUNDS_SPINNER_LABEL = "Rounds: ";

    public final String PLAY_GAME_LABEL = "PLAY GAME";
    public final String BACK_LABEL = "BACK";


    private GameSettingsState state = new GameSettingsState();

    public GameSettingsViewModel() {
        super("game settings view");
    }

    @Override
    public void firePropertyChanged() {

    }

    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.firePropertyChange("state", null, this.state);
    }

    public GameSettingsState getState() {
        return state;
    }

    public void setState(GameSettingsState state) {
        this.state = state;
    }
}
