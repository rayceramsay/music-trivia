package use_case.submit_answer;

import entity.Game;

public interface SubmitAnswerDataAccessInterface {
    public Game getGameByID(String gameId);
    public void save(Game game);
}
