package data_access.api;

import entity.Song;

import java.util.ArrayList;

public interface SongAPI {
    Song getRandomSongFromGenre(String genre);
}
