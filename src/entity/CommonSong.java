package entity;

public class CommonSong implements Song {
    private final String title;
    private final String artist;
    private final PlayableAudio audio;

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
