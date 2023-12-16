package interface_adapter.toggle_audio;

import use_case.toggle_audio.ToggleAudioInputBoundary;
import use_case.toggle_audio.ToggleAudioInputData;

public class ToggleAudioController {

    private final ToggleAudioInputBoundary toggleAudioInteractor;

    public ToggleAudioController(ToggleAudioInputBoundary toggleAudioInteractor) {
        this.toggleAudioInteractor = toggleAudioInteractor;
    }

    public void execute(String gameId) {
        ToggleAudioInputData toggleAudioInputData = new ToggleAudioInputData(gameId);
        toggleAudioInteractor.execute(toggleAudioInputData);
    }
}
