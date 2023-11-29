package entity;

/**
 * Implementation of interface Song
 */
public class CommonSong implements Song {
    private final String title;
    private final String artist;
    private final PlayableAudio audio;

    /**
     * Constructor to initialize objects of CommonSong
     *
     * @param title  title of song
     * @param artist artist of song
     * @param audio  song audio
     */
    public CommonSong(String title, String artist, PlayableAudio audio) {
        this.title = title;
        this.artist = artist;
        this.audio = audio;
    }

    /**
     * @return title of song
     */
    @Override
    public String getTitle() {
        return title;
    }

    /**
     * @return name of artist
     */
    @Override
    public String getArtist() {
        return artist;
    }

    /**
     * @return audio of song
     */
    @Override
    public PlayableAudio getAudio() {
        return audio;
    }

}
