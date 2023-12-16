package entity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Core implementation of an option based round
 */
public class MultipleChoiceRound extends BasicRound implements OptionRound {

    private final List<String> incorrectOptions;

    public MultipleChoiceRound(Song song, String question, String correctAnswer, String userAnswer, List<String> incorrectOptions) {
        super(song, question, correctAnswer, userAnswer);
        this.incorrectOptions = incorrectOptions;
    }

    public MultipleChoiceRound(Song song, String question, String correctAnswer, List<String> incorrectOptions) {
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
