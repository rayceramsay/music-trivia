package use_case.create_game;

import data_access.game_data.GameDataAccessInterface;
import entity.*;

import java.util.Objects;

/**
 * Interactor which implements the Input Boundary for the CreateGame use case
 */
public class CreateGameInteractor implements CreateGameInputBoundary {
    final GameDataAccessInterface gameAccessObject;
    final CreateGameOutputBoundary createGamePresenter;
    final RoundFactory roundFactory;

    /**
     * Constructor to initialize objects of CreateGameInteractor
     *
     * @param gameAccessObject    data access interface for CreateGame use case
     * @param createGamePresenter output boundary for CreateGame use case
     * @param roundFactory        RoundFactory
     */
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
