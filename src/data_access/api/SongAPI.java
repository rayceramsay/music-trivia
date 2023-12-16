package data_access.api;

import entity.Song;

/**
 * Represents a strategy for getting random songs
 */
public interface SongAPI {
    Song getRandomSongFromGenre(String genre);
}
