package interface_adapter.get_loadable_games;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GetLoadableGamesState {

    private List<Map<String, String>> gamesData = new ArrayList<>();
    private String errorMessage = "";

    public List<Map<String, String>> getGamesData() {
        return gamesData;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setGamesData(List<Map<String, String>> gamesData) {
        this.gamesData = gamesData;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
