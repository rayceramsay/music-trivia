package use_case.toggle_audio;


import entity.Game;
import entity.PlayableAudio;
import entity.Round;
import entity.Song;

/**
 * Interactor which implements the Input Boundary for the ToggleAudio use case
 */
public class ToggleAudioInteractor implements ToggleAudioInputBoundary {
    private final ToggleAudioGameDataAccessInterface toggleAudioDataAccessObject;
    private final ToggleAudioOutputBoundary toggleAudioPresenter;

    /**
     * Constructor to initialize objects of ToggleAudioInteractor
     *
     * @param toggleAudioDataAccessObject Data access interface for toggle audio use case
     * @param toggleAudioPresenter        Output boundary for toggle audio use case
     */
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
            songAudio.stop();
            toggleAudioPresenter.showPlayButton();
        } else {
            //playing audio and showing pause button
            songAudio.play();
            toggleAudioPresenter.showPauseButton();
        }
    }
}
