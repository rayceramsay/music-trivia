package interface_adapter.get_loadable_games;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GetLoadableGamesState {

    private List<GetLoadableGamesStateItem> gamesData = new ArrayList<>();
    private String errorMessage = "";

    public List<GetLoadableGamesStateItem> getGamesData() {
        return gamesData;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setGamesData(List<GetLoadableGamesStateItem> gamesData) {
        this.gamesData = gamesData;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
