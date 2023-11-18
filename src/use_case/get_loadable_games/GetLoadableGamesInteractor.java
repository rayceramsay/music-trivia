package use_case.get_loadable_games;

import entity.Game;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        List<Map<String, String>> loadableGamesData = new ArrayList<>();
        List<Game> loadableGames = gameDataAccessObject.getLoadableGames();

        for (Game game: loadableGames) {
            Map<String, String> gameData = new HashMap<>();

            gameData.put("gameID", game.getID());
            gameData.put("difficulty", game.getDifficulty());
            gameData.put("genre", game.getGenre());
            gameData.put("initialLives", String.valueOf(game.getInitialLives()));
            gameData.put("currentLives", String.valueOf(game.getCurrentLives()));
            gameData.put("maxRounds", String.valueOf(game.getMaxRounds()));
            gameData.put("currentRoundNumber", String.valueOf(game.getRoundsPlayed()));
            gameData.put("createdAt", game.getCreatedAt().toString());

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
