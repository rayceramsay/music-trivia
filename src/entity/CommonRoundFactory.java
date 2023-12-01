package entity;

import data_access.api.SongAPI;

import java.util.ArrayList;
import java.util.Objects;

// TODO - update this with creating a proper round

/**
 * Implementation of interface RoundFactory
 */
public class CommonRoundFactory implements RoundFactory {
    private final SongAPI songAPI;

    public CommonRoundFactory(SongAPI songAPI) {
        this.songAPI = songAPI;
    }

    /**
     * @param songGenre genre of songs to be played
     * @return A round
     */
    @Override
    public Round createHardRound(String songGenre) {
        Song song = songAPI.getRandomSongFromGenre(songGenre);
        return new TextInputRound(song, "What is the title of this song?", song.getTitle());
    }

    /**
     * @param songGenre genre of songs to be played
     * @return A round
     */
    @Override
    public Round createMediumRound(String songGenre) {
        Song song = songAPI.getRandomSongFromGenre(songGenre);
        MultipleChoiceRound round = new MultipleChoiceRound(song, "What is the title of this song?", song.getTitle());
        round.addIncorrectOptions(createIncorrectOptions(song, songGenre, 3));
        return round;
    }

    /**
     * @param songGenre genre of songs to be played
     * @return A round
     */
    @Override
    public Round createEasyRound(String songGenre) {
        Song song = songAPI.getRandomSongFromGenre(songGenre);
        MultipleChoiceRound round = new MultipleChoiceRound(song, "What is the title of this song?", song.getTitle());
        round.addIncorrectOptions(createIncorrectOptions(song, songGenre, 1));
        return round;
    }

    /*
     * Returns a list of unique (non-repeated) song titles that do not include the correct answer
     */
    private ArrayList<String> createIncorrectOptions(Song correctSong, String genre, int number) {
        ArrayList<String> options = new ArrayList<>();
        while (options.size() < number) {
            Song possibleOption = songAPI.getRandomSongFromGenre(genre);
            boolean notRepeatOption = true;
            for (String option : options) {
                if (Objects.equals(possibleOption.getTitle(), option)) {
                    notRepeatOption = false;
                }
            }
            if (!Objects.equals(possibleOption.getTitle(), correctSong.getTitle()) && notRepeatOption) {
                options.add(possibleOption.getTitle());
            }
        }
        return options;
    }
}
