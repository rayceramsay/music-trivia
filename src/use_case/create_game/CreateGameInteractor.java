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
        String ID = gameAccessObject.addGame(inputData.getDifficulty(),
                inputData.getGenre(), inputData.getRounds(), inputData.getLives());

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
