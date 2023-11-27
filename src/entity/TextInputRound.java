package entity;
public class TextInputRound implements Round {
    private final Song song;
    private final String question;
    private final String correctAnswer;
    private String userAnswer = "";

    public TextInputRound(Song song, String question, String correctAnswer) {
        this.song = song;
        this.question = question;
        this.correctAnswer = correctAnswer;
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
    public String getUserAnswer() { return userAnswer; }

    @Override
    public void setUserAnswer(String userAnswer) {
        this.userAnswer = userAnswer;
    }

    @Override
    public boolean isUserAnswerCorrect() {
        String cleanedUserAnswer = cleanString(userAnswer);
        return cleanedUserAnswer.equalsIgnoreCase(correctAnswer);
    }

    private String cleanString(String string) {
        // Strip non-printable characters and bordering white space
        return string.replaceAll("\\p{C}", "").trim();
    }
}
