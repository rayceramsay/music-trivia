package entity;

import java.util.List;

/**
 * Interface for RoundFactory
 */
public interface RoundFactory {
    Round createBasicRound(Song song, String question, String correctAnswer, String userAnswer);
    OptionRound createOptionRound(Song song, String question, String correctAnswer, String userAnswer, List<String> incorrectOptions);
    Round generateBasicRoundFromGenre(String genre);
    OptionRound generateOptionRoundFromGenre(String genre, int incorrectOptionsCount);
}
