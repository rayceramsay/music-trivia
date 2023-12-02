package use_case.toggle_audio;

import data_access.game_data.GameDataAccessInterface;
import entity.Game;
import entity.PlayableAudio;
import entity.Round;
import entity.Song;

/**
 * Interactor which implements the Input Boundary for the ToggleAudio use case
 */
public class ToggleAudioInteractor implements ToggleAudioInputBoundary {

    private final GameDataAccessInterface gameDataAccessInterface;
    private final ToggleAudioOutputBoundary toggleAudioPresenter;

    /**
     * Constructor to initialize objects of ToggleAudioInteractor
     *
     * @param gameDataAccessInterface Data access interface
     * @param toggleAudioPresenter    Output boundary for toggle audio use case
     */

    public ToggleAudioInteractor(GameDataAccessInterface gameDataAccessInterface, ToggleAudioOutputBoundary toggleAudioPresenter) {
        this.gameDataAccessInterface = gameDataAccessInterface;
        this.toggleAudioPresenter = toggleAudioPresenter;
    }

    @Override
    public void execute(ToggleAudioInputData inputData) {
        String gameID = inputData.getGameId();
        Game game = gameDataAccessInterface.getGameByID(gameID);
        Round currentRound = game.getCurrentRound();
        Song song = currentRound.getSong();
        PlayableAudio songAudio = song.getAudio();

        songAudio.setOnStopCallback(unused -> toggleAudioPresenter.showPlayButton());  // for when the song naturally ends

        boolean audioPlaying = songAudio.isPlaying();

        if (audioPlaying) {
            //stopping audio and showing play button
            songAudio.stop();
            toggleAudioPresenter.showPlayButton();
        }
        else{
            // playing audio and showing pause button
            songAudio.play();
            toggleAudioPresenter.showPauseButton();
        }
    }
}
