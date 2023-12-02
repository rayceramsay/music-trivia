package use_case.load_game;

import data_access.game_data.GameDataAccessInterface;
import data_access.game_data.InMemoryGameDataAccessObject;
import entity.*;
import interface_adapter.ViewManagerModel;
import interface_adapter.get_loadable_games.GetLoadableGamesPresenter;
import interface_adapter.get_loadable_games.GetLoadableGamesViewModel;
import interface_adapter.load_game.LoadGamePresenter;
import interface_adapter.load_game.LoadGameViewModel;
import interface_adapter.round.RoundViewModel;
import org.junit.Before;
import org.junit.Test;
import use_case.get_loadable_games.GetLoadableGamesInputBoundary;
import use_case.get_loadable_games.GetLoadableGamesInteractor;
import use_case.get_loadable_games.GetLoadableGamesOutputBoundary;
import view.RoundView;

import static org.junit.Assert.*;

public class LoadGameInteractorTest {

    private GameDataAccessInterface gameRepository;
    private Game expectedGame;
    private boolean isPresenterCalled = false;

    /**
     * Initializes gameRepository with loadable games.
     */
    @Before
    public void setupGameRepository() {
        gameRepository = new InMemoryGameDataAccessObject();
        RoundFactory roundFactory = new MockRoundFactory();

        for (int i = 0; i < 3; i++) {
            Game game = new CommonGame("hip hop " + i, "hard", 1, 3);
            Round round = roundFactory.createHardRound("hip hop " + i);
            game.setCurrentRound(round);
            gameRepository.save(game);

            if (i == 1) {
                expectedGame = game;
            }
        }
    }

    /**
     * Test that the interactor outputs the correct game data when given a loadable game ID.
     */
    @Test
    public void correctGameDataTest() {
        LoadGameInputData inputData = new LoadGameInputData(expectedGame.getID());

        LoadGameOutputBoundary presenter = new LoadGameOutputBoundary() {
            @Override
            public void prepareView(LoadGameOutputData outputData) {
                isPresenterCalled = true;

                assertEquals(expectedGame.getID(), outputData.getGameId());
                assertEquals(expectedGame.getCurrentRound().getQuestion(), outputData.getQuestion());
                assertEquals(expectedGame.getRoundsPlayed(), outputData.getCurrentRoundNumber());
                assertEquals(expectedGame.getMaxRounds(), outputData.getMaxRounds());
                assertEquals(expectedGame.getCurrentLives(), outputData.getCurrentLives());
                assertEquals(expectedGame.getInitialLives(), outputData.getInitialLives());
                assertEquals(expectedGame.getGenre(), outputData.getGenre());
            }
        };

        LoadGameInputBoundary interactor = new LoadGameInteractor(presenter, gameRepository);
        interactor.execute(inputData);

        assertTrue("Presenter did not get called", isPresenterCalled);
    }

    @Test
    public void furtherTesting(){
        LoadGameInputData inputData = new LoadGameInputData(expectedGame.getID());
        ViewManagerModel managerModel = new ViewManagerModel();
        RoundViewModel roundViewModel = new RoundViewModel(RoundView.VIEW_NAME);
        LoadGameViewModel viewModel = new LoadGameViewModel(LoadGameViewModel.STATE_PROPERTY);
        LoadGameOutputBoundary presenter = new LoadGamePresenter(managerModel, roundViewModel, viewModel);
        LoadGameInputBoundary interactor = new LoadGameInteractor(presenter, gameRepository);

        interactor.execute(inputData);
    }
}
