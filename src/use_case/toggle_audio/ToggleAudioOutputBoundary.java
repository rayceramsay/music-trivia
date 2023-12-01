package use_case.toggle_audio;

/**
 * Output boundary for toggle audio use case
 */
public interface ToggleAudioOutputBoundary {
    /**
     * Display button with pause icon
     */
    void showPauseButton();

    /**
     * Display image with play icon
     */
    void showPlayButton();

}
