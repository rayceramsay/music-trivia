package data_access;

import data_access.api.SongAPI;
import data_access.api.SpotifyAPI;
import entity.CommonSongFactory;
import entity.Song;
import org.junit.Before;
import org.junit.Test;

public class APITest {
    private SongAPI api;
    @Before
    public void init() {
        api = new SpotifyAPI(new CommonSongFactory());
    }

    @Test
    public void mainTest() {
        Song song = api.getRandomSongFromGenre("hip hop");
        assert song.getAudio() != null;
        song.getAudio().play();
        song.getAudio().setOnStopCallback(null);
        assert !song.getAudio().isPlaying();
        assert song.getAudio().getPath() != null;

    }

}
