package entity;

/**
 * Implementation of Round for text input version of user answer instead of multiple choice
 */
public class BasicRound implements Round {

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
     * @param userAnswer    answer given by user
     */
    public BasicRound(Song song, String question, String correctAnswer, String userAnswer) {
        this.song = song;
        this.question = question;
        this.correctAnswer = correctAnswer;
        this.userAnswer = userAnswer;
    }

    public BasicRound(Song song, String question, String correctAnswer) {
        this(song, question, correctAnswer, null);
    }

    @Override
    public String getQuestion() {
        return question;
    }

    @Override
    public Song getSong() {
        return song;
    }

    @Override
    public String getCorrectAnswer() {
        return correctAnswer;
    }

    @Override
    public String getUserAnswer() {
        return userAnswer;
    }

    @Override
    public void setUserAnswer(String userAnswer) {
        this.userAnswer = userAnswer;
    }

    @Override
    public boolean isUserAnswerCorrect() {
        if (userAnswer == null) {
            return false;
        }

        String cleanedUserAnswer = cleanString(userAnswer);
        return cleanedUserAnswer.equalsIgnoreCase(correctAnswer);
    }

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
