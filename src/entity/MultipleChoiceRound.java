package entity;

import org.checkerframework.checker.units.qual.A;

import java.util.ArrayList;
import java.util.Random;

public class MultipleChoiceRound implements Round{
    private final Song song;
    private final String question;
    private final String correctAnswer;
    private String userAnswer;
    private ArrayList<String> incorrectOptions;
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

    public void addIncorrectOptions(ArrayList<String> options){
        this.incorrectOptions.addAll(options);
    }
    public ArrayList<String> getRandomOrderOptions(){
        ArrayList<String> options = new ArrayList<>(this.incorrectOptions);
        int rand = new Random().nextInt(0, this.incorrectOptions.size());
        options.add(rand, this.correctAnswer);
        return options;
    }


    @Override
    public boolean isFinished() {
        return userAnswer != null;
    }

    private String cleanString(String string) {
        return string.replaceAll("\\p{C}", "").trim();
    }
}
