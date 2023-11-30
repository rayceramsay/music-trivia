package use_case.toggle_audio;

import entity.Game;
import entity.PlayableAudio;
import entity.Round;
import entity.Song;

public class ToggleAudioInteractor implements ToggleAudioInputBoundary {

    private final ToggleAudioGameDataAccessInterface toggleAudioDataAccessObject;
    private final ToggleAudioOutputBoundary toggleAudioPresenter;
    private Thread checkToggleStateThread;

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
            cancelTimerForShowingPlayButton();

            //stopping audio and showing play button
            songAudio.stop();
            toggleAudioPresenter.showPlayButton();
        }
        else {
            startTimerForShowingPlayButton(songAudio);

            // playing audio and showing pause button
            songAudio.play();
            toggleAudioPresenter.showPauseButton();
        }
    }

    private void startTimerForShowingPlayButton(PlayableAudio audio) {
        cancelTimerForShowingPlayButton();

        checkToggleStateThread = new Thread(() -> {
            try {
                int timeBetweenChecks = 20;
                int audioMaxDuration = 10000;
                int buffer = 500;

                int i = 0;
                while (i < audioMaxDuration + buffer) {
                    Thread.sleep(timeBetweenChecks);
                    if (Thread.interrupted()) {
                        return;
                    }
                    i += timeBetweenChecks;
                }

                if (!audio.isPlaying()) {
                    toggleAudioPresenter.showPlayButton();
                }
            } catch (InterruptedException ignored) {
            }
        });
        checkToggleStateThread.start();
    }

    private void cancelTimerForShowingPlayButton() {
        if (checkToggleStateThread == null) {
            return;
        }

        checkToggleStateThread.interrupt();
    }
}
