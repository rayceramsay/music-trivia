package entity;

/**
 * Represents a single song with a title, artist, and playable audio source
 */
public interface Song {
    String getTitle();
    String getArtist();
    PlayableAudio getAudio();
}
