package use_case.toggle_audio;

import entity.Game;

public interface ToggleAudioDataAccessInterface {
    Game getGameByID(String gameId);
    void save(Game game);
}
