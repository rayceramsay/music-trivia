package entity;

public class TwoMultipleChoiceRound implements Round{
    private final Song song;
    private final String question;
    private final String correctAnswer;
    private String userAnswer = "";
    public TwoMultipleChoiceRound(Song song, String question, String correctAnswer){
        this.song = song;
        this.question = question;
        this.correctAnswer = correctAnswer;

    }
    @Override
    public String getQuestion() {
        return this.question;
    }

    @Override
    public Song getSong() {
        return this.song;
    }

    @Override
    public String getCorrectAnswer() {
        return this.correctAnswer;
    }

    @Override
    public String getUserAnswer() {
        return this.userAnswer;
    }

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
