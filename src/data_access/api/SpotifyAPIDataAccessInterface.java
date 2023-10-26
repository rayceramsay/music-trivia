package data_access.api;

public interface SpotifyAPIDataAccessInterface {
    boolean expiredToken();
    void setNewToken(String token);

}
