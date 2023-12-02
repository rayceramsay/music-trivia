package data_access.api;

import entity.Song;

public interface SongAPI {
    Song getRandomSongFromGenre(String genre);
}
