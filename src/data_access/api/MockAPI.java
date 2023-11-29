package data_access.api;

import entity.CommonSong;
import entity.OnlineMP3PlayableAudio;
import entity.Song;
import entity.SongFactory;

import java.util.Random;

public class MockAPI implements SongAPI{
    private final SongFactory songFactory;
    public MockAPI(SongFactory songFactory){
        this.songFactory = songFactory;
    }
    @Override
    public Song getRandomSongFromGenre(String genre) {
        return songFactory.create(randomTitle(), "tester", "test.mp3");
    }
    private String randomTitle(){
        String CHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder songTitle = new StringBuilder();
        Random rnd = new Random();
        while (songTitle.length() < 9) { // length of the random string.
            int index = (int) (rnd.nextFloat() * CHARS.length());
            songTitle.append(CHARS.charAt(index));
        }
        return songTitle.toString();
    }
}
