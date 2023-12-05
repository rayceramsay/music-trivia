package use_case.toggle_audio;

import data_access.game_data.GameDataAccessInterface;
import data_access.game_data.InMemoryGameDataAccessObject;
import entity.*;
import interface_adapter.ViewManagerModel;
import interface_adapter.round.RoundViewModel;
import interface_adapter.submit_answer.SubmitAnswerPresenter;
import interface_adapter.submit_answer.SubmitAnswerState;
import interface_adapter.submit_answer.SubmitAnswerViewModel;
import interface_adapter.toggle_audio.ToggleAudioPresenter;
import interface_adapter.toggle_audio.ToggleAudioState;
import interface_adapter.toggle_audio.ToggleAudioViewModel;
import org.junit.Before;
import org.junit.Test;
import use_case.submit_answer.SubmitAnswerInputBoundary;
import use_case.submit_answer.SubmitAnswerInputData;
import use_case.submit_answer.SubmitAnswerInteractor;
import use_case.submit_answer.SubmitAnswerOutputBoundary;
import view.RoundView;

import static org.junit.Assert.*;

public class ToggleAudioInteractorTest {

    private Song song;
    private Game game;
    private GameDataAccessInterface gameDataAccessObject;

    @Before
    public void init() {
        song = new CommonSong("Closer", "The Chainsmokers", new MockPlayableAudio("path/song.mp3"));
        game = new CommonGame("pop", "hard", 10, 3);
        game.setCurrentRound(new BasicRound(song, "What song is this?", "Closer"));
        gameDataAccessObject = new InMemoryGameDataAccessObject();
        gameDataAccessObject.save(game);
    }

    @Test
    public void songIsNotPlayingTest() {
        song.getAudio().stop();
        ToggleAudioInputData inputData = new ToggleAudioInputData(game.getID());
        ToggleAudioOutputBoundary toggleAudioPresenter = new ToggleAudioOutputBoundary() {
            @Override
            public void showPauseButton() {
            }

            @Override
            public void showPlayButton() {
                fail("showPlayButton should not be called here");
            }
        };
        ToggleAudioInputBoundary interactor = new ToggleAudioInteractor(gameDataAccessObject, toggleAudioPresenter);
        interactor.execute(inputData);
    }

    @Test
    public void songIsPlayingTest() {
        song.getAudio().play();
        ToggleAudioInputData inputData = new ToggleAudioInputData(game.getID());
        ToggleAudioOutputBoundary toggleAudioPresenter = new ToggleAudioOutputBoundary() {
            @Override
            public void showPauseButton() {
                fail("showPlayButton should not be called here");
            }

            @Override
            public void showPlayButton() {
            }
        };
        ToggleAudioInputBoundary interactor = new ToggleAudioInteractor(gameDataAccessObject, toggleAudioPresenter);
        interactor.execute(inputData);
    }
    @Test
    public void furtherTesting () {
        song.getAudio().stop();
        ToggleAudioInputData inputData = new ToggleAudioInputData(game.getID());
        RoundViewModel roundViewModel = new RoundViewModel(RoundView.VIEW_NAME);
        ToggleAudioViewModel toggleAudioViewModel = new ToggleAudioViewModel(ToggleAudioViewModel.STATE_PROPERTY);

        ToggleAudioOutputBoundary presenter = new ToggleAudioPresenter(toggleAudioViewModel, roundViewModel);
        ToggleAudioInputBoundary interactor = new ToggleAudioInteractor(gameDataAccessObject, presenter);

        interactor.execute(inputData);

        ToggleAudioState toggleState = toggleAudioViewModel.getState();

        assert toggleState.getImgPath() == toggleAudioViewModel.getPauseButtonImagePath();
        assert song.getAudio().isPlaying();
        song.getAudio().play();

        interactor.execute(inputData);
        assert toggleState.getImgPath() == toggleAudioViewModel.getPlayButtonImagePath();
        assert !song.getAudio().isPlaying();
        assert song.getArtist() == "The Chainsmokers";
        assert song.getTitle() == "Closer";
    }
}
