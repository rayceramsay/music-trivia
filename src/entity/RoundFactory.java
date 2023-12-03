package entity;

import java.util.List;

/**
 * Interface for RoundFactory
 */
public interface RoundFactory {
    /**
     * @param song          The song
     * @param question      The prompt
     * @param correctAnswer correct answer
     * @param userAnswer    answer given by the user
     * @return Round
     */
    Round createBasicRound(Song song, String question, String correctAnswer, String userAnswer);

    /**
     * @param song             The song
     * @param question         The prompt
     * @param correctAnswer    correct answer
     * @param userAnswer       answer given by the user
     * @param incorrectOptions answers that are incorrect
     * @return OptionRound
     */
    OptionRound createOptionRound(Song song, String question, String correctAnswer, String userAnswer, List<String> incorrectOptions);

    /**
     * @param genre genre of the song
     * @return Round
     */
    Round generateBasicRoundFromGenre(String genre);

    /**
     * @param genre                 genre of the song
     * @param incorrectOptionsCount number of incorrect answers
     * @return OptionRound
     */
    OptionRound generateOptionRoundFromGenre(String genre, int incorrectOptionsCount);
}
