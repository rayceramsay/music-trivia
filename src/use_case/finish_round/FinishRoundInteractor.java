package use_case.finish_round;

import data_access.game_data.GameDataAccessInterface;
import entity.*;

import java.time.LocalDateTime;
import java.util.Objects;

public class FinishRoundInteractor implements FinishRoundInputBoundary{
    private final GameDataAccessInterface gameDataAccessObject;
    private final FinishRoundOutputBoundary finishRoundPresenter;
    private final RoundFactory roundFactory;
    public FinishRoundInteractor(FinishRoundOutputBoundary finishRoundPresenter,
                                 GameDataAccessInterface gameDataAccessObject,
                                 RoundFactory roundFactory){
        this.gameDataAccessObject = gameDataAccessObject;
        this.finishRoundPresenter = finishRoundPresenter;
        this.roundFactory = roundFactory;
        }
    @Override
    public void execute(FinishRoundInputData inputData){
        String gameId = inputData.getGameId();
        Game game = gameDataAccessObject.getGameByID(gameId);

        if(game.isGameOver()){
            game.setFinishedAt(LocalDateTime.now());
            gameDataAccessObject.save(game);

            FinishRoundOutputData outputData = new FinishRoundOutputData();
            outputData.setScore(game.getScore());

            finishRoundPresenter.prepareGameOverView(outputData);
        }else{
            String gameGenre = game.getGenre();
            String gameDifficulty = game.getDifficulty().toLowerCase().trim();
            Round nextRound;

            if(Objects.equals(gameDifficulty, "hard")){
                nextRound = roundFactory.generateBasicRoundFromGenre(gameGenre);
            }else if(Objects.equals(gameDifficulty, "medium")){
                nextRound = roundFactory.generateOptionRoundFromGenre(gameGenre, 3);
            }else{
                nextRound = roundFactory.generateOptionRoundFromGenre(gameGenre, 1);
            }
            game.setCurrentRound(nextRound);
            gameDataAccessObject.save(game);

            FinishRoundOutputData outputData = new FinishRoundOutputData();
            outputData.setGenre(gameGenre);
            outputData.setLives(game.getCurrentLives());
            outputData.setRoundNumber(game.getRoundsPlayed());
            outputData.setScore(game.getScore());

            if (nextRound instanceof OptionRound nextOptionRound) {
                outputData.setMultipleChoiceAnswers(nextOptionRound.getOptions());
            }

            finishRoundPresenter.prepareNextRoundView(outputData);
        }
    }
}
