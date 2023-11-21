package use_case.load_game;

public class LoadGameInteractor implements LoadGameInputBoundary {

    private final LoadGameOutputBoundary presenter;
    private final LoadGameGameDataAccessInterface gameRepository;

    public LoadGameInteractor(LoadGameOutputBoundary presenter, LoadGameGameDataAccessInterface gameRepository) {
        this.presenter = presenter;
        this.gameRepository = gameRepository;
    }

    @Override
    public void execute(LoadGameInputData inputData) {

    }
}
