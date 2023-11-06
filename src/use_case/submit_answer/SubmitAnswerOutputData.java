package use_case.submit_answer;

public class SubmitAnswerOutputData {
    private final boolean isUserAnswerCorrect;
    private final boolean isGameOver;
    private final String correctAnswer;

    public SubmitAnswerOutputData(boolean isUserAnswerCorrect, boolean isGameOver, String correctAnswer) {
        this.isUserAnswerCorrect = isUserAnswerCorrect;
        this.isGameOver = isGameOver;
        this.correctAnswer = correctAnswer;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public boolean isUserAnswerCorrect() {
        return isUserAnswerCorrect;
    }

    public boolean isGameOver() {
        return isGameOver;
    }
}
