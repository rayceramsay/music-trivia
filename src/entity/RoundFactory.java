package entity;
public interface RoundFactory {
    Round generateBasicRoundFromGenre(String genre);
    OptionRound generateOptionRoundFromGenre(String genre, int incorrectOptionsCount);
}
