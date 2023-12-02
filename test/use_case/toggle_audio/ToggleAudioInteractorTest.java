package use_case.toggle_audio;

import data_access.game_data.GameDataAccessInterface;
import data_access.game_data.InMemoryGameDataAccessObject;
import entity.*;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ToggleAudioInteractorTest {

    private Song song;
    private Game game;
    private GameDataAccessInterface gameDataAccessObject;

    @Before
    public void init() {
        song = new CommonSong("Closer", "The Chainsmokers", new TestPlayableAudio("path/song.mp3"));
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
}
