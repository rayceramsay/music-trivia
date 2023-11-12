package use_case.create_game;

import interface_adapter.create_game.CreateGameController;
import interface_adapter.create_game.CreateGamePresenter;
import interface_adapter.round.RoundState;
import interface_adapter.round.RoundViewModel;
import view.RoundView;

public class CreateGameInteractor implements CreateGameInputBoundary{
    final CreateGameDataAccessInterface gameAccessObject;
    final CreateGamePresenter createGamePresenter;
    final RoundViewModel roundViewModel;

    public CreateGameInteractor (CreateGameDataAccessInterface gameAccessObject, CreateGamePresenter createGamePresenter, RoundViewModel roundViewModel) {
        this.gameAccessObject = gameAccessObject;
        this.createGamePresenter = createGamePresenter;
        this.roundViewModel = roundViewModel;
    }

    @Override
    public void execute(CreateGameInputData inputData) {
        String ID = gameAccessObject.addGame(inputData.getDifficulty(),
                inputData.getGenre(),
                inputData.getLives(),
                inputData.getRounds());

        CreateGameOutputData createGameOutputData = new CreateGameOutputData();
        createGameOutputData.setGameId(ID);
        createGameOutputData.setGenre(inputData.getGenre());
        createGameOutputData.setDifficulty(inputData.getDifficulty());
        createGameOutputData.setRounds(inputData.getRounds());
        createGameOutputData.setLives(inputData.getLives());

        createGamePresenter.prepareFirstRoundView(createGameOutputData);
    }
}
