package entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MultipleChoiceRound implements Round{

    private final Song song;
    private final String question;
    private final String correctAnswer;
    private String userAnswer;
    private List<String> incorrectOptions = new ArrayList<>();

    public MultipleChoiceRound(Song song, String question, String correctAnswer, String userAnswer) {
        this.song = song;
        this.question = question;
        this.correctAnswer = correctAnswer;
        this.userAnswer = userAnswer;
    }

    public MultipleChoiceRound(Song song, String question, String correctAnswer){
        this(song, question, correctAnswer, null);
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

    @Override
    public boolean isFinished() {
        return userAnswer != null;
    }

    public void addIncorrectOptions(ArrayList<String> options){
        this.incorrectOptions.addAll(options);
    }

    public List<String> getRandomOrderOptions(){
        List<String> options = new ArrayList<>(this.incorrectOptions);
        int rand = new Random().nextInt(0, this.incorrectOptions.size());
        options.add(rand, this.correctAnswer);
        return options;
    }

    private String cleanString(String string) {
        return string.replaceAll("\\p{C}", "").trim();
    }
}
