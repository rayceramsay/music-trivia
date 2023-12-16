package use_case.toggle_audio;

public class ToggleAudioInputData {

    private final String gameId;

    public ToggleAudioInputData(String gameId) {
        this.gameId = gameId;
    }

    String getGameId() {
        return gameId;
    }
}
