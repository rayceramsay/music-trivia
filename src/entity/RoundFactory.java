package entity;
public interface RoundFactory {
    Round createHardRound(String songGenre);
    Round createMediumRound(String songGenre);
    Round createEasyRound(String songGenre);
}
