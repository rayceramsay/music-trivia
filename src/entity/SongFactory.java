package entity;

/**
 * SongFactory Interface
 */
public interface SongFactory {
    /**
     * @param title  Title of song
     * @param artist Artist of song
     * @param audio  Audio of song
     * @return A song
     */
    Song create(String title, String artist, String audio);
}
