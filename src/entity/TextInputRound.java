package entity;

public class TextInputRound implements Round {

    private String userAnswer;
    @Override
    public String getQuestion() {
        return null;
    }

    @Override
    public String getSong() {
        return null;
    }

    @Override
    public String getCorrectAnswer() {
        return null;
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
    public boolean isAnswerCorrect(String answer) {
        return false;
    }
}
