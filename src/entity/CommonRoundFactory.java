package entity;

import data_access.api.SongAPI;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CommonRoundFactory implements RoundFactory{
    private final SongAPI songAPI;
    public CommonRoundFactory(SongAPI songAPI){
        this.songAPI = songAPI;
    }

    @Override
    public Round generateBasicRoundFromGenre(String songGenre) {
        Song song = songAPI.getRandomSongFromGenre(songGenre);

        return new BasicRound(song, "What is the title of this song?", song.getTitle());
    }

    @Override
    public OptionRound generateOptionRoundFromGenre(String songGenre, int incorrectOptionsCount) {
        Song song = songAPI.getRandomSongFromGenre(songGenre);
        List<String> incorrectOptions = createIncorrectOptions(song, songGenre, incorrectOptionsCount);

        return new MultipleChoiceRound(song, "What is the title of this song?", song.getTitle(), incorrectOptions);
    }

    /*
    * Returns a list of unique (non-repeated) song titles that do not include the correct answer
    */
    private ArrayList<String> createIncorrectOptions(Song correctSong, String genre, int number) {
        ArrayList<String> options = new ArrayList<>();
        while (options.size() < number){
            Song possibleOption = songAPI.getRandomSongFromGenre(genre);
            boolean notRepeatOption = true;
            for(String option: options){
                if(Objects.equals(possibleOption.getTitle(), option)){
                    notRepeatOption = false;
                }
            }
            if(!Objects.equals(possibleOption.getTitle(), correctSong.getTitle()) && notRepeatOption){
                options.add(possibleOption.getTitle());
            }
        }
        return options;
    }
}