package use_case.toggle_audio;

/**
 * Input boundary interface for ToggleAudio use case
 */
public interface ToggleAudioInputBoundary {
    /**
     * If the song is already playing, stop the audio and show the play button.
     * If the song is not already playing, play the audio and show the pause button.
     *
     * @param inputData The input data object holding the game ID
     */
    void execute(ToggleAudioInputData inputData);
}
