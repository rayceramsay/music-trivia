package use_case.finish_round;

import entity.Game;

public interface FinishRoundGameDataAccessInterface {
    Game getGameByID(String gameId);
    void save(Game game);
}
