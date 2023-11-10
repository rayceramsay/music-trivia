package use_case.toggle_audio;

import entity.Game;

public interface ToggleAudioDataAccessInterface {
    public Game getGameByID(String gameId);
    public void save(Game game);
}
