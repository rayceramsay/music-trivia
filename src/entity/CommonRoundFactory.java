package entity;
// TODO - update this with creating a proper round
public class CommonRoundFactory implements RoundFactory{
    @Override
    public Round createHardRound(String songGenre) {
        PlayableAudio audio = new FileMP3PlayableAudio("path");
        Song song = new CommonSong("title", "artist", audio);
        return new TextInputRound(song, "What is this song?", song.getTitle());
    }

    @Override
    public Round createMediumRound (String songGenre) {
        PlayableAudio audio = new FileMP3PlayableAudio("path");
        Song song = new CommonSong("title", "artist", audio);
        return new FourMultipleChoiceRound(song, "What is this song?", song.getTitle());
    }
    @Override
    public Round createEasyRound (String songGenre) {
        PlayableAudio audio = new FileMP3PlayableAudio("path");
        Song song = new CommonSong("title", "artist", audio);
        return new TwoMultipleChoiceRound(song, "What is this song?", song.getTitle());
    }
}
