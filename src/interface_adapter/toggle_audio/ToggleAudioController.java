package interface_adapter.toggle_audio;

import use_case.toggle_audio.ToggleAudioInputBoundary;
import use_case.toggle_audio.ToggleAudioInputData;

/**
 * Controller for toggle audio interface adapter
 */
public class ToggleAudioController {
    private final ToggleAudioInputBoundary toggleAudioInteractor;

    /**
     * Constructor to initialize objects of ToggleAudioInteractor
     *
     * @param toggleAudioInteractor toggle audio input boundary
     */
    public ToggleAudioController(ToggleAudioInputBoundary toggleAudioInteractor) {
        this.toggleAudioInteractor = toggleAudioInteractor;
    }

    public void execute(String gameId) {
        ToggleAudioInputData toggleAudioInputData = new ToggleAudioInputData(gameId);
        toggleAudioInteractor.execute(toggleAudioInputData);
    }
}
