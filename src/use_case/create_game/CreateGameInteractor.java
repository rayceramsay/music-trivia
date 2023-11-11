package use_case.create_game;

import interface_adapter.create_game.CreateGameController;

public class CreateGameInteractor implements CreateGameInputBoundary{
    final CreateGameDataAccessInterface gameAccessObject;

    public CreateGameInteractor (CreateGameDataAccessInterface gameAccessObject) {
        this.gameAccessObject = gameAccessObject;
    }

    @Override
    public void execute(CreateGameInputData inputData) {
        String ID = gameAccessObject.addGame(inputData.getDifficulty(),
                inputData.getGenre(),
                inputData.getLives(),
                inputData.getRounds());
    }
}
