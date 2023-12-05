package entity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Extension of Basic Round for multiple choice version of gameplay
 */
public class MultipleChoiceRound extends BasicRound implements OptionRound {

    private final List<String> incorrectOptions;

    /**
     * Constructor that initializes objects of MultipleChoiceRound
     *
     * @param song             A song
     * @param question         the prompt for the round
     * @param correctAnswer    correct answer of the round
     * @param userAnswer       answer give by user
     * @param incorrectOptions incorrect answer options
     */
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
