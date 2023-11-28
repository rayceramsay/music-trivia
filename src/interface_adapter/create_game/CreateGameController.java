package interface_adapter.create_game;

import use_case.create_game.CreateGameInputBoundary;
import use_case.create_game.CreateGameInputData;
import use_case.create_game.CreateGameInteractor;

public class CreateGameController {
    final CreateGameInputBoundary createGameInteractor;

    public CreateGameController (CreateGameInputBoundary createGameInteractor) {
        this.createGameInteractor = createGameInteractor;
    }

    public void execute(String difficulty, String genre, int lives, int rounds) {
        CreateGameInputData inputData = new CreateGameInputData(genre, difficulty, lives, rounds);
        createGameInteractor.execute(inputData);
    }
}
