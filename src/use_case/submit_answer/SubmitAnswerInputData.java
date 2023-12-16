package use_case.submit_answer;

public class SubmitAnswerInputData {

    private final String userAnswer;
    private final String gameId;

    public SubmitAnswerInputData(String userAnswer, String gameId) {
        this.userAnswer = userAnswer;
        this.gameId = gameId;
    }

    String getUserAnswer() {
        return userAnswer;
    }

    String getGameId() {
        return gameId;
    }
}
