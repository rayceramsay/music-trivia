package data_access.api;

import entity.Song;

/**
 * Interface for song API
 */
public interface SongAPI {
    /**
     * Randomly gets a song from an array of songs from the given genre,
     * provided its preview url is a string.
     *
     * @param genre genre of the song
     * @return A song
     */
    Song getRandomSongFromGenre(String genre);
}
