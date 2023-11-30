package interface_adapter.get_loadable_games;

import java.util.ArrayList;
import java.util.List;

/**
 * State for GetLoadableGames interface adapter
 */
public class GetLoadableGamesState {

    private List<GetLoadableGamesStateItem> gamesData = new ArrayList<>();
    private String errorMessage = "";

    /**
     * @return list of loadable games including their data
     */
    public List<GetLoadableGamesStateItem> getGamesData() {
        return gamesData;
    }

    /**
     * @return error message for case where there are no loadable games
     */
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
