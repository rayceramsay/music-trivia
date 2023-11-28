package use_case.create_game;

import entity.*;
import interface_adapter.create_game.CreateGameController;
import interface_adapter.create_game.CreateGamePresenter;
import interface_adapter.round.RoundState;
import interface_adapter.round.RoundViewModel;
import view.RoundView;

public class CreateGameInteractor implements CreateGameInputBoundary{
    final CreateGameDataAccessInterface gameAccessObject;
    final CreateGameOutputBoundary createGamePresenter;
    final RoundViewModel roundViewModel;

    public CreateGameInteractor (CreateGameDataAccessInterface gameAccessObject, CreateGameOutputBoundary createGamePresenter, RoundViewModel roundViewModel) {
        this.gameAccessObject = gameAccessObject;
        this.createGamePresenter = createGamePresenter;
        this.roundViewModel = roundViewModel;
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
