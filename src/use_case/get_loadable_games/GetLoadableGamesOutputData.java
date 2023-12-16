package use_case.get_loadable_games;

import java.util.List;

public class GetLoadableGamesOutputData {

    private final List<GetLoadableGamesOutputDataItem> getLoadableGamesOutputDataItems;

    public GetLoadableGamesOutputData(List<GetLoadableGamesOutputDataItem> loadableGamesData) {
        this.getLoadableGamesOutputDataItems = loadableGamesData;
    }

    public List<GetLoadableGamesOutputDataItem> getGetLoadableGamesOutputDataItems() {
        return getLoadableGamesOutputDataItems;
    }
}
