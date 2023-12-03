package use_case.toggle_audio;

public class ToggleAudioInputData {
    private final String gameId;

    /**
     * Constructor to initialize objects of ToggleAudioInputData
     *
     * @param gameId GameID
     */
    public ToggleAudioInputData(String gameId) {
        this.gameId = gameId;
    }

    String getGameId() {
        return gameId;
    }
}
