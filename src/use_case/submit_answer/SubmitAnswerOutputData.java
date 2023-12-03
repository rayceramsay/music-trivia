package use_case.submit_answer;

public class SubmitAnswerOutputData {
    private final boolean isUserAnswerCorrect;
    private final String correctAnswer;

    /**
     * Constructor to initialize objects of SubmitAnswerOutputData
     *
     * @param isUserAnswerCorrect boolean value for if given answer is correct
     * @param correctAnswer       correct answer of round
     */
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
