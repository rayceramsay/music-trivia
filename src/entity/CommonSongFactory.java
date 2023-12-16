package entity;

public class CommonSongFactory implements SongFactory {

    @Override
    public Song create(String title, String artist, PlayableAudio audio) {
        return new CommonSong(title, artist, audio);
    }
}
