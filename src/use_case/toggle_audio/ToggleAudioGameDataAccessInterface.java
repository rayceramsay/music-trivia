package use_case.toggle_audio;

import entity.Game;

public interface ToggleAudioGameDataAccessInterface {
    Game getGameByID(String gameId);
    void save(Game game);
}
