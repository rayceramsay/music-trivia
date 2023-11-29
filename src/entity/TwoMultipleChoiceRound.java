package entity;

/**
 * Implementation of Round for 2 possible multiple choice answers
 */
public class TwoMultipleChoiceRound implements Round {
    private final Song song;
    private final String question;
    private final String correctAnswer;
    private String userAnswer;

    /**
     * Constructor to initialize objects of TwoMultipleChoiceRound
     * @param song          song of round
     * @param question      prompt of round
     * @param correctAnswer correct answer of round
     */
    public TwoMultipleChoiceRound(Song song, String question, String correctAnswer) {
        this.song = song;
        this.question = question;
        this.correctAnswer = correctAnswer;

    }

    /**
     * @return prompt of round
     */
    @Override
    public String getQuestion() {
        return this.question;
    }

    /**
     * @return song of round
     */
    @Override
    public Song getSong() {
        return this.song;
    }

    /**
     * @return correct answer of round.
     */
    @Override
    public String getCorrectAnswer() {
        return this.correctAnswer;
    }

    /**
     * @return answer given by user.
     */
    @Override
    public String getUserAnswer() {
        return this.userAnswer;
    }

    /**
     * @param userAnswer answer given by user
     */
    @Override
    public void setUserAnswer(String userAnswer) {
        this.userAnswer = userAnswer;
    }

    /**
     * @return if answer given by user is correct.
     */
    @Override
    public boolean isUserAnswerCorrect() {
        if (userAnswer == null) {
            return false;
        }

        String cleanedUserAnswer = cleanString(userAnswer);
        return cleanedUserAnswer.equalsIgnoreCase(correctAnswer);
    }

    /**
     * @return if round is finished.
     */
    @Override
    public boolean isFinished() {
        return userAnswer != null;
    }

    /**
     * @param string input string
     * @return string stripped of non-printable characters and bordering white space.
     */
    private String cleanString(String string) {
        return string.replaceAll("\\p{C}", "").trim();
    }
}
