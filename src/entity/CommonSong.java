package entity;

public class CommonSong implements Song {
    private String title;
    private String artist;
    private PlayableAudio audio;

    public CommonSong(String title, String artist, PlayableAudio audio) {
        this.title = title;
        this.artist = artist;
        this.audio = audio;
    }
    @Override
    public String getTitle() {
        return title;
    }
    @Override
    public String getArtist() {
        return artist;
    }

    @Override
    public PlayableAudio getAudio() {
        return audio;
    }
}