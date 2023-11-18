package use_case.get_loadable_games;

import java.util.List;
import java.util.Map;

public class GetLoadableGamesOutputData {
    private final List<Map<String, String>> loadableGamesData; // keys: gameID, genre, difficulty, initialLives,
                                                              // currentLives, maxRounds, currentRoundNumber, createdAt

    public GetLoadableGamesOutputData(List<Map<String, String>> loadableGamesData) {
        this.loadableGamesData = loadableGamesData;
    }

    public List<Map<String, String>> getLoadableGamesData() {
        return loadableGamesData;
    }
}
