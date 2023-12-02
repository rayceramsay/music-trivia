package entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

public class MultipleChoiceRound extends BasicRound implements OptionRound {

    private final List<String> incorrectOptions;

    public MultipleChoiceRound(Song song, String question, String correctAnswer, String userAnswer, List<String> incorrectOptions){
        super(song, question, userAnswer, correctAnswer);
        this.incorrectOptions = incorrectOptions;
    }

    public MultipleChoiceRound(Song song, String question, String correctAnswer, List<String> incorrectOptions){
        this(song, question, correctAnswer, null, incorrectOptions);
    }

    @Override
    public List<String> getOptions() {
        List<String> shuffledOptions = new ArrayList<>();
        shuffledOptions.add(getCorrectAnswer());
        shuffledOptions.addAll(incorrectOptions);
        Collections.shuffle(shuffledOptions);

        return shuffledOptions;
    }
}
