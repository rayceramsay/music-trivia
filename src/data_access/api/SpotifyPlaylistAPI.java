package data_access.api;

import entity.PlayableAudio;
import entity.PlayableAudioFactory;
import entity.Song;
import entity.SongFactory;
import okhttp3.*;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Implementation of SongAPI where we have playlists for each specific genre
 */
public class SpotifyPlaylistAPI implements SongAPI {

    private final String clientId;
    private final String clientSecret;
    private final OkHttpClient client;
    private final SongFactory songFactory;
    private final PlayableAudioFactory playableAudioFactory;
    private String authToken;
    private final Map<String, String> playlists = new HashMap<>();

    public SpotifyPlaylistAPI(SongFactory songFactory, PlayableAudioFactory playableAudioFactory, String clientId, String clientSecret) {
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        this.client = new OkHttpClient().newBuilder().build();
        this.songFactory = songFactory;
        this.playableAudioFactory = playableAudioFactory;
        this.playlists.put("Pop", "357fWKFTiDhpt9C69CMG4q");
        this.playlists.put("Rap", "4riovLwMCrY3q0Cd4e0Sqp");
        this.playlists.put("Rock", "4MnDPgHgfuEPX43dw2gxDn");
    }

    @Override
    public Song getRandomSongFromGenre(String genre) {
        JSONObject responseBody = retrieveSongData(genre);
        JSONArray songsArray = responseBody.getJSONObject("tracks").getJSONArray("items");

        JSONObject item;
        Song song = null;

        do {
            int i = new Random().nextInt(0, songsArray.length());
            item = ((JSONObject) songsArray.get(i)).getJSONObject("track");
            if (item.get("preview_url") instanceof String) {
                JSONObject albumArtistInfo = (JSONObject) item.getJSONObject("album").getJSONArray("artists").get(0);
                String songName = item.getString("name");
                String audioUrl = item.getString("preview_url");
                String artistName = albumArtistInfo.getString("name");
                PlayableAudio songAudio = playableAudioFactory.create(audioUrl);
                song = songFactory.create(songName, artistName, songAudio);
            }
        } while (!(item.get("preview_url") instanceof String));

        return song == null ? getRandomSongFromGenre(genre) : song;
    }

    private JSONObject retrieveSongData(String genre) {
        String url = "https://api.spotify.com/v1/playlists/" + playlists.get(genre);
        Request request = new Request.Builder()
                .url(url)
                .addHeader("Authorization", "Bearer " + authToken)
                .addHeader("Content-Type", "application/json")
                .build();

        try (Response responseRequest = client.newCall(request).execute()) {
            if (responseRequest.code() == 401) { // Code 401 - bad (expired or null) auth token
                generateAuthToken();
                return retrieveSongData(genre); // recall api with auth token
            }
            if (responseRequest.body() == null) {
                throw new RuntimeException("Error - no response body");
            }

            return new JSONObject(responseRequest.body().string());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void generateAuthToken() {
        String endpoint = "https://accounts.spotify.com/api/token";
        RequestBody body = new FormBody.Builder()
                .add("client_id", clientId)
                .add("client_secret", clientSecret)
                .add("grant_type", "client_credentials")
                .build();
        Request request = new Request.Builder()
                .url(endpoint)
                .addHeader("Content-Type", "application/x-www-form-urlencoded")
                .post(body)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (response.body() == null) {
                throw new RuntimeException("Error - no response body");
            }

            JSONObject responseBody = new JSONObject(response.body().string());
            authToken = responseBody.getString("access_token");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}



