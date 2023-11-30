package use_case.exit_round;

import entity.Game;
import entity.PlayableAudio;

public class ExitRoundInteractor implements ExitRoundInputBoundary {

    private final ExitRoundOutputBoundary exitRoundOutputBoundary;
    private final ExitRoundGameDataAccessInterface exitRoundGameDataAccessInterface;

    public ExitRoundInteractor(ExitRoundOutputBoundary exitRoundOutputBoundary, ExitRoundGameDataAccessInterface exitRoundGameDataAccessInterface) {
        this.exitRoundOutputBoundary = exitRoundOutputBoundary;
        this.exitRoundGameDataAccessInterface = exitRoundGameDataAccessInterface;
    }

    @Override
    public void execute(ExitRoundInputData inputData) {
        Game game = exitRoundGameDataAccessInterface.getGameByID(inputData.getGameID());
        PlayableAudio roundAudio = game.getCurrentRound().getSong().getAudio();
        roundAudio.stop();

        exitRoundOutputBoundary.prepareView();
    }
}
