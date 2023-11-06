package entity;

import java.util.Objects;

public class TextInputRound implements Round {

    private String userAnswer;
    private final Song song;
    private final String question;
    private final String correctAnswer;
    public TextInputRound(Song song, String question, String userAnswer, String correctAnswer){
        this.song = song;
        this.question = question;
        this.correctAnswer = correctAnswer;
        this.userAnswer = userAnswer;
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
    public boolean isAnswerCorrect(String answer) {
        return Objects.equals(userAnswer, correctAnswer);
    }
}
