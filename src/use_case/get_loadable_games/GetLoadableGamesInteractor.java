package use_case.get_loadable_games;

import entity.Game;

import java.time.format.DateTimeFormatter;
import java.util.*;

public class GetLoadableGamesInteractor implements GetLoadableGamesInputBoundary {

    private final GetLoadableGamesOutputBoundary presenter;
    private final GetLoadableGamesGameDataAccessInterface gameDataAccessObject;

    public GetLoadableGamesInteractor(GetLoadableGamesOutputBoundary presenter,
                                      GetLoadableGamesGameDataAccessInterface gameDataAccessObject) {
        this.presenter = presenter;
        this.gameDataAccessObject = gameDataAccessObject;
    }

    @Override
    public void execute() {
        List<GetLoadableGamesOutputDataItem> loadableGamesData = new ArrayList<>();
        List<Game> loadableGames = gameDataAccessObject.getLoadableGames();

        for (Game game: loadableGames) {
            String createdAt = game.getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
            GetLoadableGamesOutputDataItem gameData = new GetLoadableGamesOutputDataItem(game.getID(), game.getGenre(),
                    game.getDifficulty(), game.getInitialLives(), game.getCurrentLives(), game.getMaxRounds(),
                    game.getRoundsPlayed(), createdAt);

            loadableGamesData.add(gameData);
        }

        if (loadableGamesData.isEmpty()) {
            presenter.prepareNoGamesExistView("No games available to load!");
        } else {
            GetLoadableGamesOutputData outputData = new GetLoadableGamesOutputData(loadableGamesData);
            presenter.prepareGamesExistView(outputData);
        }
    }
}
