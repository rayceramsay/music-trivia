package use_case.exit_round;

import data_access.game_data.GameDataAccessInterface;
import entity.Game;
import entity.PlayableAudio;

/**
 * Interactor which implements the Input Boundary for the ExitRound use case
 */
public class ExitRoundInteractor implements ExitRoundInputBoundary {

    private final ExitRoundOutputBoundary exitRoundOutputBoundary;
    private final GameDataAccessInterface gameDataAccessInterface;

    /**
     * Constructor to initialize objects of ExitRoundInteractor
     *
     * @param exitRoundOutputBoundary output boundary for ExitRound use case
     * @param gameDataAccessInterface data access interface for ExitRound use case
     */
    public ExitRoundInteractor(ExitRoundOutputBoundary exitRoundOutputBoundary, GameDataAccessInterface gameDataAccessInterface) {
        this.exitRoundOutputBoundary = exitRoundOutputBoundary;
        this.gameDataAccessInterface = gameDataAccessInterface;
    }

    @Override
    public void execute(ExitRoundInputData inputData) {
        Game game = gameDataAccessInterface.getGameByID(inputData.getGameID());
        PlayableAudio roundAudio = game.getCurrentRound().getSong().getAudio();
        roundAudio.stop();

        exitRoundOutputBoundary.prepareView();
    }
}
