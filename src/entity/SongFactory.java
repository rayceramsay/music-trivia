package entity;

public interface SongFactory {
    Song create(String title, String artist, String audio);
}
