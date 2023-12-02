package use_case.create_game;

import data_access.game_data.GameDataAccessInterface;
import entity.*;

import java.util.Objects;

public class CreateGameInteractor implements CreateGameInputBoundary{
    final GameDataAccessInterface gameAccessObject;
    final CreateGameOutputBoundary createGamePresenter;
    final RoundFactory roundFactory;

    public CreateGameInteractor (GameDataAccessInterface gameAccessObject,
                                 CreateGameOutputBoundary createGamePresenter,
                                 RoundFactory roundFactory) {
        this.gameAccessObject = gameAccessObject;
        this.createGamePresenter = createGamePresenter;
        this.roundFactory = roundFactory;
    }

    @Override
    public void execute(CreateGameInputData inputData) {
        String ID = gameAccessObject.addGame(inputData.getDifficulty(),
                inputData.getGenre(),
                inputData.getLives(),
                inputData.getRounds());

        String genre = inputData.getGenre();
        String difficulty = inputData.getDifficulty().toLowerCase().trim();

        Round firstRound;

        if (Objects.equals(difficulty, "hard")) {
            firstRound = roundFactory.createHardRound(genre);
        }
        else if (Objects.equals(difficulty, "medium")){
            firstRound = roundFactory.createMediumRound(genre);
        }
        else {
            firstRound = roundFactory.createEasyRound(genre);
        }
        Game game = gameAccessObject.getGameByID(ID);
        game.setCurrentRound(firstRound);
        gameAccessObject.save(game);

        CreateGameOutputData createGameOutputData = new CreateGameOutputData();
        createGameOutputData.setGameId(ID);
        createGameOutputData.setGenre(inputData.getGenre());
        createGameOutputData.setDifficulty(inputData.getDifficulty());
        createGameOutputData.setRounds(inputData.getRounds());
        createGameOutputData.setLives(inputData.getLives());
        if (firstRound instanceof OptionRound firstOptionRound) {
            createGameOutputData.setMultipleChoiceAnswers(firstOptionRound.getOptions());
        }

        createGamePresenter.prepareFirstRoundView(createGameOutputData);
    }
}
