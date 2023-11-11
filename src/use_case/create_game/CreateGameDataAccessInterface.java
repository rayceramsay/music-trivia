package use_case.create_game;

public interface CreateGameDataAccessInterface {
    String addGame(String difficulty, String genre, int lives, int rounds);
}
