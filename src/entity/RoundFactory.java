package entity;

/**
 * Interface for RoundFactory
 */
public interface RoundFactory {
    /**
     * @param songGenre genre of song
     * @return A hard difficulty round.
     */
    Round createHardRound(String songGenre);

    /**
     * @param songGenre genre of song
     * @return A medium difficulty round.
     */
    Round createMediumRound(String songGenre);

    /**
     * @param songGenre genre of song
     * @return A easy difficulty round.
     */
    Round createEasyRound(String songGenre);
}
