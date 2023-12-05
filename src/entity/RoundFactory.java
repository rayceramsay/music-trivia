package entity;

import java.util.List;

/**
 * Interface for RoundFactory
 */
public interface RoundFactory {
    /**
     * Create a text input round
     *
     * @param song          The song
     * @param question      The prompt
     * @param correctAnswer correct answer
     * @param userAnswer    answer given by the user
     * @return Round
     */
    Round createBasicRound(Song song, String question, String correctAnswer, String userAnswer);

    /**
     * create a multiple choice round
     *
     * @param song             The song
     * @param question         The prompt
     * @param correctAnswer    correct answer
     * @param userAnswer       answer given by the user
     * @param incorrectOptions answers that are incorrect
     * @return OptionRound
     */
    OptionRound createOptionRound(Song song, String question, String correctAnswer, String userAnswer, List<String> incorrectOptions);

    /**
     * create a text input round with songs of the given genre
     *
     * @param genre genre of the song
     * @return Round
     */
    Round generateBasicRoundFromGenre(String genre);

    /**
     * create a multiple choice round with songs of the given genre and amount of incorrect choices
     *
     * @param genre                 genre of the song
     * @param incorrectOptionsCount number of incorrect answers
     * @return OptionRound
     */
    OptionRound generateOptionRoundFromGenre(String genre, int incorrectOptionsCount);
}
