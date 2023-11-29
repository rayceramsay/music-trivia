package use_case.toggle_audio;


import data_access.game_data.GameDataAccessInterface;
import entity.Game;
import entity.PlayableAudio;
import entity.Round;
import entity.Song;

public class ToggleAudioInteractor implements ToggleAudioInputBoundary {
    private final GameDataAccessInterface toggleAudioDataAccessObject;
    private final ToggleAudioOutputBoundary toggleAudioPresenter;

    public ToggleAudioInteractor(GameDataAccessInterface toggleAudioDataAccessObject, ToggleAudioOutputBoundary toggleAudioPresenter) {
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
            songAudio.stop();
            toggleAudioPresenter.showPlayButton();
        }
        else {
            //playing audio and showing pause button
            songAudio.play();
            toggleAudioPresenter.showPauseButton();
        }
    }
}
