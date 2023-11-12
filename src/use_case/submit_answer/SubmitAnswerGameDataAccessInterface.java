package use_case.submit_answer;

import entity.Game;

public interface SubmitAnswerGameDataAccessInterface {
    Game getGameByID(String gameId);
    void save(Game game);
}
