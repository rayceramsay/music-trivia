package use_case.toggle_audio;


public class ToggleAudioInteractor implements ToggleAudioInputBoundary {
    private final ToggleAudioDataAccessInterface toggleAudioDataAccessObject;
    private final ToggleAudioOutputBoundary toggleAudioPresenter;

    public ToggleAudioInteractor(ToggleAudioDataAccessInterface toggleAudioDataAccessObject, ToggleAudioOutputBoundary toggleAudioPresenter) {
        this.toggleAudioDataAccessObject = toggleAudioDataAccessObject;
        this.toggleAudioPresenter = toggleAudioPresenter;
    }

    @Override
    public void execute(ToggleAudioInputData inputData) {
        //TODO implement toggle audio use case logic (start playing song from spotify when audio button is clicked,
        // pause if it is clicked again
        // - should not block main thread (should be able to click answer while song is playing)
        // - change button icon from pause to play depending on state
    }
}
