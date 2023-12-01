package entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

public class MultipleChoiceRound implements Round {

    private final Song song;
    private final String question;
    private final String correctAnswer;
    private String userAnswer;
    private final List<String> incorrectOptions;

    public MultipleChoiceRound(Song song, String question, String correctAnswer){
        this.song = song;
        this.question = question;
        this.correctAnswer = correctAnswer;
        this.incorrectOptions = new ArrayList<>();
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
        if (userAnswer == null) {
            return false;
        }

        String cleanedUserAnswer = cleanString(userAnswer);
        return cleanedUserAnswer.equalsIgnoreCase(correctAnswer);
    }

    public void addIncorrectOptions(List<String> options){
        this.incorrectOptions.addAll(options);
    }

    @Override
    public boolean isFinished() {
        return userAnswer != null;
    }

    @Override
    public List<String> getMultipleChoiceAnswers() {
        List<String> ret = new ArrayList<>();
        ret.add(correctAnswer);
        ret.addAll(incorrectOptions);
        Collections.shuffle(ret);
        return ret;
    }

    private String cleanString(String string) {
        return string.replaceAll("\\p{C}", "").trim();
    }
}
