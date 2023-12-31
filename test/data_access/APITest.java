package data_access;

import data_access.api.SongAPI;
import data_access.api.SpotifyAPI;
import entity.*;
import io.github.cdimascio.dotenv.Dotenv;
import org.junit.Before;
import org.junit.Test;

public class APITest {

    private final static String SPOTIFY_CLIENT_ID = Dotenv.load().get("SPOTIFY_CLIENT_ID");
    private final static String SPOTIFY_CLIENT_SECRET = Dotenv.load().get("SPOTIFY_CLIENT_SECRET");

    private SongAPI api;

    @Before
    public void init() {
        SongFactory songFactory = new CommonSongFactory();
        PlayableAudioFactory playableAudioFactory = new MockPlayableAudioFactory();
        api = new SpotifyAPI(songFactory, playableAudioFactory, SPOTIFY_CLIENT_ID, SPOTIFY_CLIENT_SECRET);
    }

    @Test
    public void mainTest() {
        Song song = api.getRandomSongFromGenre("hip hop");
        assert song.getAudio() != null;
        song.getAudio().play();
        song.getAudio().setOnStopCallback(null);
        assert song.getAudio().isPlaying();
        assert song.getAudio().getPath() != null;
    }

}
