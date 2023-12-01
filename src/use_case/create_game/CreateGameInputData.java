package use_case.create_game;

public class CreateGameInputData {
    private final String genre;
    private final String difficulty;
    private final int lives;
    private final int rounds;

    /**
     * Constructor to initialize objects of CreateGameInputData
     *
     * @param genre      genre of songs used
     * @param difficulty difficulty level of Game
     * @param lives      amount of lives in Game
     * @param rounds     number of rounds in game
     */
    public CreateGameInputData(String genre, String difficulty, int lives, int rounds) {
        this.genre = genre;
        this.difficulty = difficulty;
        this.lives = lives;
        this.rounds = rounds;
    }

    public int getRounds() {
        return rounds;
    }

    public String getGenre() {
        return genre;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public int getLives() {
        return lives;
    }
}
