package entity;

/**
 * Core implementation of a text based round
 */
public class BasicRound implements Round {

    private final Song song;
    private final String question;
    private final String correctAnswer;
    private String userAnswer;

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
     * Return the input string but stripped of non-printable characters and bordering white space.
     *
     * @param string the input string to clean
     * @return the cleaned version of the input string
     */
    private String cleanString(String string) {
        return string.replaceAll("\\p{C}", "").trim();
    }
}
