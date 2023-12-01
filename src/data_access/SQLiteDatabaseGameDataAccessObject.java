package data_access;

import entity.*;
import org.sqlite.SQLiteConfig;
import use_case.create_game.CreateGameDataAccessInterface;
import use_case.finish_round.FinishRoundGameDataAccessInterface;
import use_case.get_loadable_games.GetLoadableGamesGameDataAccessInterface;
import use_case.load_game.LoadGameGameDataAccessInterface;
import use_case.statistics.StatisticsDataAccessInterface;
import use_case.submit_answer.SubmitAnswerGameDataAccessInterface;
import use_case.toggle_audio.ToggleAudioGameDataAccessInterface;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class SQLiteDatabaseGameDataAccessObject implements SubmitAnswerGameDataAccessInterface, FinishRoundGameDataAccessInterface,
        CreateGameDataAccessInterface, GetLoadableGamesGameDataAccessInterface, LoadGameGameDataAccessInterface,
        StatisticsDataAccessInterface, ToggleAudioGameDataAccessInterface {

    private final static DateTimeFormatter sqliteDateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");

    private final String databasePath;
    private final InMemoryGameDataAccessObject cache = new InMemoryGameDataAccessObject();

    public SQLiteDatabaseGameDataAccessObject(String databasePath) {
        this.databasePath = databasePath;

        try {
            setupDatabase();
            populateCache();
        } catch (SQLException e) {
            throw new RuntimeException("Could not setup SQLite database object.");
        }
    }

    public boolean gameExists(Game game) {
        return cache.gameExists(game);
    }

    @Override
    public Game getGameByID(String gameID) {
        return cache.getGameByID(gameID);
    }

    @Override
    public List<Game> getLoadableGames() {
        return cache.getLoadableGames();
    }

    @Override
    public LifetimeStatistics avgStats() {
        return cache.avgStats();
    }

    @Override
    public void save(Game game) {
        Connection connection = getConnection();
        try {
            connection.setAutoCommit(false);
            deleteGame(game, connection);
            insertNewGame(game, connection, false);
            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ignored) {}

            throw new RuntimeException("A problem occurred while saving the game.");
        } finally {
            try {
                connection.close();
            } catch (SQLException ignored) {}
        }

        cache.save(game);
    }

    public void clear() {
        try {
            destroyDatabase();
            setupDatabase();
        } catch (SQLException e) {
            throw new RuntimeException("A problem occurred while clearing the database.");
        }

        cache.clear();
    }

    private Game createBaseGameFromResultSet(ResultSet resultSet) throws SQLException {
        String externalId = resultSet.getString("external_id");
        String difficulty = resultSet.getString("difficulty");
        String genre = resultSet.getString("genre");
        int initialLives = resultSet.getInt("initial_lives");
        int maxRounds = resultSet.getInt("max_rounds");
        int currentLives = resultSet.getInt("current_lives");
        int score = resultSet.getInt("score");
        LocalDateTime createdAt = LocalDateTime.parse(resultSet.getString("created_at"), sqliteDateFormatter);
        String rawFinishedAt = resultSet.getString("finished_at");
        LocalDateTime finishedAt = rawFinishedAt == null ? null : LocalDateTime.parse(rawFinishedAt, sqliteDateFormatter);

        return new CommonGame(externalId, genre, difficulty, maxRounds, initialLives, currentLives, score, createdAt, finishedAt);
    }

    private void populateGameWithExistingRounds(Game game, int internalGameId, Connection connection) throws SQLException {
        String selectRoundDataSql = """
                SELECT r.question, r.correct_answer, r.user_answer, s.title, s.artist, s.audio_path
                FROM game_round AS gr
                INNER JOIN round AS r ON r.id = gr.id
                INNER JOIN song AS s ON s.round_id = r.id
                WHERE gr.game_id = ?
                ORDER BY r.created_at
                """;

        try (PreparedStatement statement = connection.prepareStatement(selectRoundDataSql)) {
            statement.setInt(1, internalGameId);
            ResultSet roundDataResults = statement.executeQuery();

            while (roundDataResults.next()) {
                String question = roundDataResults.getString("question");
                String correctAnswer = roundDataResults.getString("correct_answer");
                String userAnswer = roundDataResults.getString("user_answer");
                String songTitle = roundDataResults.getString("title");
                String songArtist = roundDataResults.getString("artist");
                String songAudioPath = roundDataResults.getString("audio_path");

                Song song = new CommonSong(songTitle, songArtist, new OnlineMP3PlayableAudio(songAudioPath));

                Round round;
                if (game.getDifficulty().equalsIgnoreCase("easy") || game.getDifficulty().equalsIgnoreCase("medium")) {
                   round = new MultipleChoiceRound(song, question, correctAnswer, userAnswer);
                } else {
                    round = new TextInputRound(song, question, correctAnswer, userAnswer);
                }

                // TODO: populate round with multiple choice options - requires changes to DB schema

                game.setCurrentRound(round);
            }
        }
    }

    private void deleteGame(Game game, Connection connection) throws SQLException {
        String deleteGameSql = "DELETE FROM game WHERE external_id = ?";

        try (PreparedStatement statement = connection.prepareStatement(deleteGameSql)) {
            statement.setString(1, game.getID());
            statement.executeUpdate();
        }
    }

    private void insertNewGame(Game game, Connection connection, boolean useTransaction) throws SQLException {
        String insertGameSql = """
            INSERT INTO game (external_id, difficulty, genre, initial_lives, max_rounds, current_lives, score, created_at, finished_at)
            VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)
            """;
        String insertRoundSql = "INSERT INTO round (game_id, question, correct_answer, user_answer) VALUES (?, ?, ?, ?)";
        String insertSongSql = "INSERT INTO song (round_id, title, artist, audio_path) VALUES (?, ?, ?, ?)";
        String insertGameRoundSql = "INSERT INTO game_round (game_id, round_id) VALUES (?, ?)";

        try {
            if (useTransaction) {
                connection.setAutoCommit(false);
            }

            try (PreparedStatement insertGameStatement = connection.prepareStatement(insertGameSql)) {
                insertGameStatement.setString(1, game.getID());
                insertGameStatement.setString(2, game.getDifficulty());
                insertGameStatement.setString(3, game.getGenre());
                insertGameStatement.setInt(4, game.getInitialLives());
                insertGameStatement.setInt(5, game.getMaxRounds());
                insertGameStatement.setInt(6, game.getCurrentLives());
                insertGameStatement.setInt(7, game.getScore());
                insertGameStatement.setString(8, game.getCreatedAt().format(sqliteDateFormatter));
                String finishedAt = game.getFinishedAt() != null ? game.getFinishedAt().format(sqliteDateFormatter) : null;
                insertGameStatement.setString(9, finishedAt);
                insertGameStatement.executeUpdate();
            }

            int internalGameId = getLastInsertedRowid(connection);

            for (Round round : game.getRounds()) {
                try (PreparedStatement insertRoundStatement = connection.prepareStatement(insertRoundSql)) {
                    insertRoundStatement.setInt(1, internalGameId);
                    insertRoundStatement.setString(2, round.getQuestion());
                    insertRoundStatement.setString(3, round.getCorrectAnswer());
                    insertRoundStatement.setString(4, round.getUserAnswer());
                    insertRoundStatement.executeUpdate();
                }

                int internalRoundId = getLastInsertedRowid(connection);

                try (PreparedStatement insertSongStatement = connection.prepareStatement(insertSongSql)) {
                    Song song = round.getSong();
                    insertSongStatement.setInt(1, internalRoundId);
                    insertSongStatement.setString(2, song.getTitle());
                    insertSongStatement.setString(3, song.getArtist());
                    insertSongStatement.setString(4, song.getAudio().getPath());
                    insertSongStatement.executeUpdate();
                }

                try (PreparedStatement insertGameRoundStatement = connection.prepareStatement(insertGameRoundSql)) {
                    insertGameRoundStatement.setInt(1, internalGameId);
                    insertGameRoundStatement.setInt(2, internalRoundId);
                    insertGameRoundStatement.executeUpdate();
                }
            }

            if (useTransaction) {
                connection.commit();
            }
        } catch (SQLException e) {
            if (useTransaction) {
                connection.rollback();
            }
            throw e;
        }
    }

    private int getLastInsertedRowid(Connection connection) throws SQLException {
        String getLastInsertedRowidSql = "SELECT last_insert_rowid()";

        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(getLastInsertedRowidSql);
            return resultSet.getInt(1);
        }
    }

    private void populateCache() throws SQLException {
        String selectLoadableGamesSql = """
                SELECT id, external_id, difficulty, genre, initial_lives, max_rounds, current_lives, score, created_at, finished_at
                FROM game""";

        try (Connection connection = getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet gameResults = statement.executeQuery(selectLoadableGamesSql);

            while (gameResults.next()) {
                Game game = createBaseGameFromResultSet(gameResults);
                int internalGameId = gameResults.getInt("id");
                populateGameWithExistingRounds(game, internalGameId, connection);

                cache.save(game);
            }
        }
    }

    private void setupDatabase() throws SQLException {
        String createGameTableSql = """
                CREATE TABLE IF NOT EXISTS game (
                    id INTEGER PRIMARY KEY,
                    external_id TEXT NOT NULL UNIQUE,
                    difficulty TEXT NOT NULL,
                    genre TEXT NOT NULL,
                    initial_lives INTEGER NOT NULL,
                    max_rounds INTEGER NOT NULL,
                    current_lives INTEGER NOT NULL,
                    score INTEGER NOT NULL,
                    created_at TEXT NOT NULL,
                    finished_at TEXT
                );
                """;
        String createRoundTableSql = """
                CREATE TABLE IF NOT EXISTS round (
                    id INTEGER PRIMARY KEY,
                    game_id INTEGER NOT NULL,
                    question TEXT NOT NULL,
                    correct_answer TEXT NOT NULL,
                    user_answer TEXT,
                    created_at TEXT NOT NULL DEFAULT CURRENT_TIMESTAMP,
                    FOREIGN KEY (game_id) REFERENCES game (id) ON DELETE CASCADE
                );
                """;
        String createSongTableSql = """
                CREATE TABLE IF NOT EXISTS song (
                    id INTEGER PRIMARY KEY,
                    round_id INTEGER NOT NULL UNIQUE,
                    title TEXT NOT NULL,
                    artist TEXT NOT NULL,
                    audio_path TEXT NOT NULL,
                    FOREIGN KEY (round_id) REFERENCES round (id) ON DELETE CASCADE
                );
                """;
        String createGameRoundTableSql = """
                CREATE TABLE IF NOT EXISTS game_round (
                    id INTEGER PRIMARY KEY,
                    game_id INTEGER NOT NULL,
                    round_id INTEGER NOT NULL,
                    FOREIGN KEY (game_id) REFERENCES game (id) ON DELETE CASCADE,
                    FOREIGN KEY (round_id) REFERENCES round (id) ON DELETE CASCADE,
                    UNIQUE(game_id, round_id)
                );
                """;

        try (Connection connection = getConnection();
             Statement statement = connection.createStatement()) {
            statement.execute(createGameTableSql);
            statement.execute(createRoundTableSql);
            statement.execute(createSongTableSql);
            statement.execute(createGameRoundTableSql);
        }
    }

    private void destroyDatabase() throws SQLException {
        Connection connection = getConnection();

        try (Statement statement = connection.createStatement()) {
            connection.setAutoCommit(false);

            String[] tableNames = new String[]{"game_round", "song", "round", "game"};
            for (String tableName : tableNames) {
                statement.execute("DROP TABLE " + tableName);
            }

            connection.commit();
        } catch (SQLException e) {
            connection.rollback();
            throw e;
        } finally {
            connection.close();
        }
    }

    private Connection getConnection() {
        String databaseUrl = "jdbc:sqlite:" + databasePath;

        try {
            Class.forName("org.sqlite.JDBC");
            SQLiteConfig config = new SQLiteConfig();
            config.enforceForeignKeys(true);

            return DriverManager.getConnection(databaseUrl, config.toProperties());
        } catch (SQLException e) {
            throw new RuntimeException("Could not connect to the database.");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}