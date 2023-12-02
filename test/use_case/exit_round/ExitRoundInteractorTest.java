package use_case.exit_round;

import data_access.game_data.GameDataAccessInterface;
import data_access.game_data.InMemoryGameDataAccessObject;
import entity.CommonGame;
import entity.Game;
import entity.MockRoundFactory;
import entity.RoundFactory;
import interface_adapter.ViewManagerModel;
import interface_adapter.exit_round.ExitRoundPresenter;
import interface_adapter.exit_round.ExitRoundViewModel;
import interface_adapter.load_game.LoadGamePresenter;
import interface_adapter.load_game.LoadGameViewModel;
import interface_adapter.round.RoundViewModel;
import interface_adapter.toggle_audio.ToggleAudioViewModel;
import org.junit.Before;
import org.junit.Test;
import use_case.load_game.LoadGameInputBoundary;
import use_case.load_game.LoadGameInputData;
import use_case.load_game.LoadGameInteractor;
import use_case.load_game.LoadGameOutputBoundary;
import view.RoundView;

import static org.junit.Assert.*;

public class ExitRoundInteractorTest {

    private boolean presenterGetsCalled;
    private GameDataAccessInterface gameRepository;
    private Game game;

    /**
     * Initialize each test with a game DAO pre-populated with an in-progress game.
     */
    @Before
    public void init() {
        presenterGetsCalled = false;
        gameRepository = new InMemoryGameDataAccessObject();

        String genre = "country";
        game = new CommonGame(genre, "hard", 10, 10);
        RoundFactory roundFactory = new MockRoundFactory();
        game.setCurrentRound(roundFactory.createHardRound(genre));
        gameRepository.save(game);
    }

    /**
     * Test that the round song audio isn't playing.
     */
    @Test
    public void songIsNotPlayingTest() {
        game.getCurrentRound().getSong().getAudio().play();

        ExitRoundOutputBoundary presenter = new ExitRoundOutputBoundary() {
            @Override
            public void prepareView() {
                presenterGetsCalled = true;
                assertFalse(game.getCurrentRound().getSong().getAudio().isPlaying());
            }
        };
        ExitRoundInputBoundary interactor = new ExitRoundInteractor(presenter, gameRepository);
        ExitRoundInputData inputData = new ExitRoundInputData(game.getID());

        interactor.execute(inputData);

        assertTrue(presenterGetsCalled);
    }
    @Test
    public void furtherTesting(){
        ExitRoundInputData inputData = new ExitRoundInputData(game.getID());
        ViewManagerModel managerModel = new ViewManagerModel();
        RoundViewModel roundViewModel = new RoundViewModel(RoundView.VIEW_NAME);
        ExitRoundViewModel viewModel = new ExitRoundViewModel(LoadGameViewModel.STATE_PROPERTY);
        ToggleAudioViewModel toggleAudioViewModel = new ToggleAudioViewModel(ToggleAudioViewModel.STATE_PROPERTY);
        ExitRoundOutputBoundary presenter = new ExitRoundPresenter(managerModel, viewModel, roundViewModel,toggleAudioViewModel);
        ExitRoundInputBoundary interactor = new ExitRoundInteractor(presenter, gameRepository);

        interactor.execute(inputData);

    }
}
