package entity;

/**
 * Interface for Song
 */
public interface Song {
    /**
     * @return Title of song.
     */
    String getTitle();

    /**
     * @return Artist of song.
     */
    String getArtist();

    /**
     * @return Song Audio.
     */
    PlayableAudio getAudio();
}
