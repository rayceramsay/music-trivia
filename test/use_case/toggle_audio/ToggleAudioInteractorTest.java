package use_case.toggle_audio;

import entity.*;
import org.junit.Before;
import org.junit.Test;

public class ToggleAudioInteractorTest {
    //TODO write tests ensuring that the toggle audio interactor behaves as expected
    //check following scenarios:
    //  - original state is pause
    //  - when button is clicked, icon changes to play and audio starts playing
    //  - when audio is playing and button is clicked, audio is paused and icon changes to pause

    private final String PLAY_IMAGE_PATH = "play-img.png";
    private final String PAUSE_IMAGE_PATH = "pause-img.png";
    private ToggleAudioGameDataAccessInterface gameDataAccessObject;
    private Game game;

    @Before
    public void init() {
        Game game = new CommonGame("pop", "hard", 10, 3);
        Song song = new CommonSong("Closer", "The Chainsmokers", new OnlineMP3PlayableAudio("path/song.mp3"));
        Round round = new TextInputRound(song, "What song is this?", "Closer");
        game.setCurrentRound(round);
        gameDataAccessObject.save(game);
    }

    @Test
    public void audioPlayingTest() {
        ToggleAudioInputData inputData = new ToggleAudioInputData(game.getID());
    }
}
