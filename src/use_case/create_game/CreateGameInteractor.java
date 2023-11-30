package use_case.create_game;

import entity.*;

import java.util.Objects;

public class CreateGameInteractor implements CreateGameInputBoundary {

    final CreateGameDataAccessInterface gameAccessObject;
    final CreateGameOutputBoundary createGamePresenter;
    final RoundFactory roundFactory;

    public CreateGameInteractor(CreateGameDataAccessInterface gameAccessObject,
                                CreateGameOutputBoundary createGamePresenter,
                                RoundFactory roundFactory) {
        this.gameAccessObject = gameAccessObject;
        this.createGamePresenter = createGamePresenter;
        this.roundFactory = roundFactory;
    }

    @Override
    public void execute(CreateGameInputData inputData) {
        Game game = new CommonGame(inputData.getGenre(), inputData.getDifficulty(), inputData.getRounds(), inputData.getLives());

        String genre = inputData.getGenre();
        String difficulty = inputData.getDifficulty().toLowerCase().trim();

        Round firstRound;
        if (Objects.equals(difficulty, "easy")) {
            firstRound = roundFactory.createEasyRound(genre);
        } else if (Objects.equals(difficulty, "medium")) {
            firstRound = roundFactory.createMediumRound(genre);
        } else {
            firstRound = roundFactory.createHardRound(genre);
        }

        game.setCurrentRound(firstRound);
        gameAccessObject.save(game);

        CreateGameOutputData createGameOutputData = new CreateGameOutputData();
        createGameOutputData.setGameId(game.getID());
        createGameOutputData.setGenre(game.getGenre());
        createGameOutputData.setDifficulty(game.getDifficulty());
        createGameOutputData.setRounds(game.getMaxRounds());
        createGameOutputData.setLives(game.getInitialLives());

        createGamePresenter.prepareFirstRoundView(createGameOutputData);
    }
}
