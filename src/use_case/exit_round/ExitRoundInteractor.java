package use_case.exit_round;

import data_access.game_data.GameDataAccessInterface;
import entity.Game;
import entity.PlayableAudio;

public class ExitRoundInteractor implements ExitRoundInputBoundary {

    private final ExitRoundOutputBoundary exitRoundOutputBoundary;
    private final GameDataAccessInterface gameDataAccessInterface;

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
