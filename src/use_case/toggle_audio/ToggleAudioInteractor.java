package use_case.toggle_audio;


import entity.Game;
import entity.PlayableAudio;
import entity.Round;
import entity.Song;

public class ToggleAudioInteractor implements ToggleAudioInputBoundary {
    private final ToggleAudioGameDataAccessInterface toggleAudioDataAccessObject;
    private final ToggleAudioOutputBoundary toggleAudioPresenter;

    public ToggleAudioInteractor(ToggleAudioGameDataAccessInterface toggleAudioDataAccessObject, ToggleAudioOutputBoundary toggleAudioPresenter) {
        this.toggleAudioDataAccessObject = toggleAudioDataAccessObject;
        this.toggleAudioPresenter = toggleAudioPresenter;
    }

    @Override
    public void execute(ToggleAudioInputData inputData) {
        //TODO implement toggle audio use case logic (start playing song from spotify when audio button is clicked,
        // pause if it is clicked again
        // - should not block main thread (should be able to click answer while song is playing)
        // - change button icon from pause to play depending on state
        String gameID = inputData.getGameId();
        Game game = toggleAudioDataAccessObject.getGameByID(gameID);
        Round currentRound = game.getCurrentRound();

        Song song = currentRound.getSong();
        PlayableAudio songAudio = song.getAudio();

        boolean audioPlaying = !songAudio.isPlaying();
        System.out.println(audioPlaying);
        ToggleAudioOutputData toggleAudioOutputData = new ToggleAudioOutputData(audioPlaying);

        if (audioPlaying) {
            toggleAudioPresenter.preparePauseButton(toggleAudioOutputData);
            System.out.println("prepare pause button");
            songAudio.stop();
            System.out.println("song is now paused");
        }
        else {
            toggleAudioPresenter.preparePlayButton(toggleAudioOutputData);
            System.out.println("prepare play button");
            songAudio.play();
            System.out.println("song is now paused");
        }
//        boolean audioNotPlaying = songAudio.isPaused();
//        System.out.println(audioNotPlaying);
//        ToggleAudioOutputData toggleAudioOutputData = new ToggleAudioOutputData(audioNotPlaying);
//
//        if (audioNotPlaying) {
//            toggleAudioPresenter.preparePauseButton(toggleAudioOutputData);
//            System.out.println("prepare pause button");
//            songAudio.play();
//            System.out.println("song is not playing");
//        }
//        else {
//            toggleAudioPresenter.preparePlayButton(toggleAudioOutputData);
//            System.out.println("prepare play button");
//            songAudio.stop();
//            System.out.println("song is now paused");
//        }

    }
}
