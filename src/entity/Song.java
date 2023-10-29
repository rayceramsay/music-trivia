package entity;

/*
 * Entity representing a playable song.
 *
 * Representation invariants:
 *   - <audioSource> should be compatible with <songPlayer> (e.g. an MP3 audio source should be used with MP3SongPlayer)
 */
public class Song {
    private final String title;
    private final String artist;
    private final AudioSource audioSource;
    private final SongPlayer songPlayer;

    public Song(String title, String artist, AudioSource audioSource, SongPlayer songPlayer) {
        this.title = title;
        this.artist = artist;
        this.audioSource = audioSource;
        this.songPlayer = songPlayer;
    }

    public void playAudio() {
        songPlayer.playAudio(audioSource);
    }

    public void stopAudio() { this.songPlayer.stopAudio(); }

    @Override
    public String toString() {
        return String.format("%s - %s", this.artist, this.title);
    }

    public String getTitle() { return this.title; }

    public String getArtist() { return this.artist; }
}
