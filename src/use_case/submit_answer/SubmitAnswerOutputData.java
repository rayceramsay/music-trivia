package use_case.submit_answer;

public class SubmitAnswerOutputData {

    private final boolean isUserAnswerCorrect;
    private final String correctAnswer;

    public SubmitAnswerOutputData(boolean isUserAnswerCorrect, String correctAnswer) {
        this.isUserAnswerCorrect = isUserAnswerCorrect;
        this.correctAnswer = correctAnswer;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public boolean isUserAnswerCorrect() {
        return isUserAnswerCorrect;
    }

}
