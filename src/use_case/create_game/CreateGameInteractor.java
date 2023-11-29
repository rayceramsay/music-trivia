package use_case.create_game;

import entity.*;

public class CreateGameInteractor implements CreateGameInputBoundary{
    final CreateGameDataAccessInterface gameAccessObject;
    final CreateGameOutputBoundary createGamePresenter;

    public CreateGameInteractor (CreateGameDataAccessInterface gameAccessObject, CreateGameOutputBoundary createGamePresenter) {
        this.gameAccessObject = gameAccessObject;
        this.createGamePresenter = createGamePresenter;
    }

    @Override
    public void execute(CreateGameInputData inputData) {
        Game game = new CommonGame(inputData.getGenre(), inputData.getDifficulty(), inputData.getRounds(), inputData.getLives());

        // temporary, will use factory in future
        PlayableAudio audio = new FileMP3PlayableAudio("path");
        Song song = new CommonSong("title", "artist", audio);
        Round firstRound = new TextInputRound(song, "What is this song?", song.getTitle());

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
