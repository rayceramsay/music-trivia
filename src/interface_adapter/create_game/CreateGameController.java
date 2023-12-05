package interface_adapter.create_game;

import use_case.create_game.CreateGameInputBoundary;
import use_case.create_game.CreateGameInputData;

/**
 * Controller for CreateGame interface adapter
 */
public class CreateGameController {
    final CreateGameInputBoundary createGameInteractor;

    /**
     * Constructor to initialize objects of CreateGameController
     *
     * @param createGameInteractor createGame Input Boundary
     */
    public CreateGameController(CreateGameInputBoundary createGameInteractor) {
        this.createGameInteractor = createGameInteractor;
    }

    /**
     * Method to execute the interactor using the given input data
     *
     * @param difficulty difficulty of game
     * @param genre      genre of songs played in the game
     * @param lives      amount of lives set for the game
     * @param rounds     number of rounds in the game
     */
    public void execute(String difficulty, String genre, int lives, int rounds) {
        CreateGameInputData inputData = new CreateGameInputData(genre, difficulty, lives, rounds);
        createGameInteractor.execute(inputData);
    }
}
