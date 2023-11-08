package use_case.submit_answer;

import entity.Game;

public interface SubmitAnswerDataAccessInterface {
    Game getGameByID(String gameId);
    void save(Game game);
}
