package use_case.get_loadable_games;

import java.util.List;

public class GetLoadableGamesOutputData {

    private final List<GetLoadableGamesOutputDataItem> getLoadableGamesOutputDataItems;

    /**
     * Constructor to initialize objects of GetLoadableGamesOutputData
     *
     * @param loadableGamesData list of output data components of the game
     */
    public GetLoadableGamesOutputData(List<GetLoadableGamesOutputDataItem> loadableGamesData) {
        this.getLoadableGamesOutputDataItems = loadableGamesData;
    }

    public List<GetLoadableGamesOutputDataItem> getGetLoadableGamesOutputDataItems() {
        return getLoadableGamesOutputDataItems;
    }
}
