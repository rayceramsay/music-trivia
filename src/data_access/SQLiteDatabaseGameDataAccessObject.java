package data_access;

import io.github.cdimascio.dotenv.Dotenv;
import java.sql.*;

public class SQLiteDatabaseGameDataAccessObject {

    private final String databasePath;
    private final String databaseName;
    private Connection sharedConnection;

    public SQLiteDatabaseGameDataAccessObject() {
        Dotenv dotenv = Dotenv.load();
        databasePath = dotenv.get("DB_PATH");
        databaseName = dotenv.get("DB_NAME");

        setupDatabase();
    }

    private Connection getConnection() {
        if (sharedConnection != null) {
            return sharedConnection;
        }

        String databaseUrl = "jdbc:sqlite:" + databasePath + databaseName + ".db";
        try {
            sharedConnection = DriverManager.getConnection(databaseUrl);
        } catch (SQLException e) {
            throw new RuntimeException("Could not connect to the database.");
        }

        return sharedConnection;
    }

    private void closeConnection() {
        if (sharedConnection == null) {
            return;
        }

        try {
            sharedConnection.close();
        } catch (SQLException e) {
            throw new RuntimeException("Could not close connection to the database.");
        } finally {
            sharedConnection = null;
        }
    }

    private void setupDatabase() {
        String createGameTableSql = """
                CREATE TABLE IF NOT EXISTS game (
                    id INTEGER PRIMARY KEY,
                    difficulty TEXT NOT NULL,
                    genre TEXT NOT NULL,
                    initial_lives INTEGER NOT NULL,
                    max_rounds INTEGER NOT NULL,
                    current_lives INTEGER NOT NULL,
                    score INTEGER NOT NULL DEFAULT 0,
                    created_at TEXT NOT NULL DEFAULT CURRENT_TIMESTAMP,
                    finished_at TEXT
                );
                """;
        String createSongTableSql = """
                CREATE TABLE IF NOT EXISTS song (
                    id INTEGER PRIMARY KEY,
                    title TEXT NOT NULL,
                    artist TEXT NOT NULL,
                    audio_path TEXT NOT NULL
                );
                """;
        String createRoundTableSql = """
                CREATE TABLE IF NOT EXISTS round (
                    id INTEGER PRIMARY KEY,
                    game_id INTEGER NOT NULL,
                    song_id INTEGER NOT NULL,
                    question TEXT NOT NULL,
                    correct_answer TEXT NOT NULL,
                    user_answer TEXT,
                    created_at TEXT NOT NULL DEFAULT CURRENT_TIMESTAMP,
                    FOREIGN KEY (game_id) REFERENCES game (id),
                    FOREIGN KEY (song_id) REFERENCES song (id)
                );
                """;
        String createGameRoundTableSql = """
                CREATE TABLE IF NOT EXISTS game_round (
                    id INTEGER PRIMARY KEY,
                    game_id INTEGER NOT NULL,
                    round_id INTEGER NOT NULL,
                    FOREIGN KEY (game_id) REFERENCES game (id),
                    FOREIGN KEY (round_id) REFERENCES round (id)
                );
                """;

        Connection connection = getConnection();
        try {
            Statement statement = connection.createStatement();

            // Create database tables if they don't exist
            statement.execute(createGameTableSql);
            statement.execute(createSongTableSql);
            statement.execute(createRoundTableSql);
            statement.execute(createGameRoundTableSql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            closeConnection();
        }
    }
}
