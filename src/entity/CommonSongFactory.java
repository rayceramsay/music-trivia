package entity;

/**
 * Implementation of interface SongFactory
 */
public class CommonSongFactory implements SongFactory {
    public CommonSongFactory() {
    }

    @Override
    public Song create(String title, String artist, String audio) {
        PlayableAudio playableAudio = createPlayableAudio(audio);
        return new CommonSong(title, artist, playableAudio);
    }

    private PlayableAudio createPlayableAudio(String audio) {
        return new OnlineMP3PlayableAudio(audio);
    }
}
