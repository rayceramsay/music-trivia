package use_case.create_game;

import data_access.game_data.GameDataAccessInterface;
import entity.*;

public class CreateGameInteractor implements CreateGameInputBoundary {

    private final GameDataAccessInterface gameAccessObject;
    private final CreateGameOutputBoundary createGamePresenter;
    private final RoundFactory roundFactory;

    public CreateGameInteractor(GameDataAccessInterface gameAccessObject,
                                CreateGameOutputBoundary createGamePresenter,
                                RoundFactory roundFactory) {
        this.gameAccessObject = gameAccessObject;
        this.createGamePresenter = createGamePresenter;
        this.roundFactory = roundFactory;
    }

    @Override
    public void execute(CreateGameInputData inputData) {
        Game game = new CommonGame(inputData.getGenre(), inputData.getDifficulty(), inputData.getRounds(), inputData.getLives());

        String difficulty = inputData.getDifficulty().toLowerCase().trim();
        String genre = inputData.getGenre();
        Round firstRound;

        if (difficulty.equals("easy")) {
            firstRound = roundFactory.generateOptionRoundFromGenre(genre, 1);
        }
        else if (difficulty.equals("medium")){
            firstRound = roundFactory.generateOptionRoundFromGenre(genre, 3);
        }
        else {
            firstRound = roundFactory.generateBasicRoundFromGenre(genre);
        }

        game.setCurrentRound(firstRound);
        gameAccessObject.save(game);

        CreateGameOutputData createGameOutputData = new CreateGameOutputData();
        createGameOutputData.setGameId(game.getID());
        createGameOutputData.setGenre(game.getGenre());
        createGameOutputData.setDifficulty(game.getDifficulty());
        createGameOutputData.setRounds(game.getMaxRounds());
        createGameOutputData.setLives(game.getInitialLives());

        if (firstRound instanceof OptionRound firstOptionRound) {
            createGameOutputData.setMultipleChoiceAnswers(firstOptionRound.getOptions());
        }

        createGamePresenter.prepareFirstRoundView(createGameOutputData);
    }
}
