package use_case.toggle_audio;


import entity.Game;
import entity.PlayableAudio;
import entity.Round;
import entity.Song;

import java.sql.SQLOutput;

public class ToggleAudioInteractor implements ToggleAudioInputBoundary {
    private final ToggleAudioGameDataAccessInterface toggleAudioDataAccessObject;
    private final ToggleAudioOutputBoundary toggleAudioPresenter;

    public ToggleAudioInteractor(ToggleAudioGameDataAccessInterface toggleAudioDataAccessObject, ToggleAudioOutputBoundary toggleAudioPresenter) {
        this.toggleAudioDataAccessObject = toggleAudioDataAccessObject;
        this.toggleAudioPresenter = toggleAudioPresenter;
    }

    @Override
    public void execute(ToggleAudioInputData inputData) {
        String gameID = inputData.getGameId();
        Game game = toggleAudioDataAccessObject.getGameByID(gameID);
        Round currentRound = game.getCurrentRound();

        Song song = currentRound.getSong();
        PlayableAudio songAudio = song.getAudio();

        boolean audioPlaying = songAudio.isPlaying();

        if (audioPlaying) {
            //stopping audio and showing play button
            toggleAudioPresenter.showPlayButton();
            songAudio.stop();
        }
        else {
            //playing audio and showing pause button
            toggleAudioPresenter.showPauseButton();
            songAudio.play();

        }
    }
}
