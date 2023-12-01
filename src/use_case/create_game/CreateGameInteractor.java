package use_case.create_game;

import entity.*;

/**
 * Interactor which implements the Input Boundary for the CreateGame use case
 */
public class CreateGameInteractor implements CreateGameInputBoundary {
    final CreateGameDataAccessInterface gameAccessObject;
    final CreateGameOutputBoundary createGamePresenter;

    /**
     * Constructor to initialize objects of CreateGameInteractor
     *
     * @param gameAccessObject    data access interface for CreateGame use case
     * @param createGamePresenter output boundary for CreateGame use case
     */
    public CreateGameInteractor(CreateGameDataAccessInterface gameAccessObject, CreateGameOutputBoundary createGamePresenter) {
        this.gameAccessObject = gameAccessObject;
        this.createGamePresenter = createGamePresenter;
    }

    @Override
    public void execute(CreateGameInputData inputData) {
        String ID = gameAccessObject.addGame(inputData.getDifficulty(),
                inputData.getGenre(),
                inputData.getLives(),
                inputData.getRounds());

        // temporary, will use factory in future
        PlayableAudio audio = new FileMP3PlayableAudio("path");
        Song song = new CommonSong("title", "artist", audio);
        Round firstRound = new TextInputRound(song, "What is this song?", song.getTitle());

        gameAccessObject.getGameByID(ID).setCurrentRound(firstRound);

        CreateGameOutputData createGameOutputData = new CreateGameOutputData();
        createGameOutputData.setGameId(ID);
        createGameOutputData.setGenre(inputData.getGenre());
        createGameOutputData.setDifficulty(inputData.getDifficulty());
        createGameOutputData.setRounds(inputData.getRounds());
        createGameOutputData.setLives(inputData.getLives());

        createGamePresenter.prepareFirstRoundView(createGameOutputData);
    }
}
