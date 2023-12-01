package entity;

/**
 * Implementation of Round for text input version of user answer instead of multiple choice
 */
public class TextInputRound implements Round {
    private final Song song;
    private final String question;
    private final String correctAnswer;
    private String userAnswer;

    /**
     * Constructor to initialize objects of TextInputRound
     *
     * @param song          song of round
     * @param question      prompt of round
     * @param correctAnswer correct answer of round
     */
    public TextInputRound(Song song, String question, String correctAnswer) {
        this.song = song;
        this.question = question;
        this.correctAnswer = correctAnswer;
    }

    /**
     * @return Prompt of round.
     */
    @Override
    public String getQuestion() {
        return question;
    }

    /**
     * @return Song of round.
     */
    @Override
    public Song getSong() {
        return song;
    }

    /**
     * @return Correct answer of round.
     */
    @Override
    public String getCorrectAnswer() {
        return correctAnswer;
    }

    /**
     * @return Answer from user.
     */
    @Override
    public String getUserAnswer() {
        return userAnswer;
    }

    /**
     * @param userAnswer answer given by user
     */
    @Override
    public void setUserAnswer(String userAnswer) {
        this.userAnswer = userAnswer;
    }

    /**
     * @return If answer given by user is correct.
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
     * @return If round is finished.
     */
    @Override
    public boolean isFinished() {
        return userAnswer != null;
    }

    /**
     * @param string input string
     * @return String stripped of non-printable characters and bordering white space.
     */
    private String cleanString(String string) {
        return string.replaceAll("\\p{C}", "").trim();
    }
}
