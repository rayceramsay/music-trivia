package data_access.api;

import entity.PlayableAudio;
import entity.PlayableAudioFactory;
import entity.Song;
import entity.SongFactory;
import okhttp3.*;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Random;

public class SpotifyAPI implements SongAPI {

    private final static int POPULARITY_THRESHOLD = 83;

    private final String clientId;
    private final String clientSecret;
    private final OkHttpClient client;
    private final SongFactory songFactory;
    private final PlayableAudioFactory playableAudioFactory;
    private String authToken;

    public SpotifyAPI(SongFactory songFactory, PlayableAudioFactory playableAudioFactory, String clientId, String clientSecret) {
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        this.client = new OkHttpClient().newBuilder().build();
        this.songFactory = songFactory;
        this.playableAudioFactory = playableAudioFactory;
    }

    @Override
    public Song getRandomSongFromGenre(String genre) {
        JSONObject responseBody = retrieveSongData(genre);
        JSONArray songsArray = responseBody.getJSONObject("tracks").getJSONArray("items");

        JSONObject item;
        Song song = null;


        do {
            int i = new Random().nextInt(75);
            item = (JSONObject) songsArray.get(i);
            if (item.get("preview_url") instanceof String) {
                JSONObject albumArtistInfo = (JSONObject) item.getJSONObject("album").getJSONArray("artists").get(0);
                String songName = item.getString("name");
                String audioUrl = item.getString("preview_url");
                String artistName = albumArtistInfo.getString("name");
                PlayableAudio songAudio = playableAudioFactory.create(audioUrl);
                song = songFactory.create(songName, artistName, songAudio);
            }
            i++;
        } while (!(item.get("preview_url") instanceof String) || item.getInt("popularity") < POPULARITY_THRESHOLD);

        return song == null ? getRandomSongFromGenre(genre) : song;
    }

    private JSONObject retrieveSongData(String genre) {
        String url = getRandomEndpoint(genre);
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

    private String getRandomEndpoint(String genre) {
        Random random = new Random();

        String baseEndpoint = "https://api.spotify.com/v1/search?q=";
        char search = (char) ('a' + random.nextInt(26));
        int offset = random.nextInt(10);

        return String.format("%s%s&genre:%s&type=track&limit=50&offset=%s", baseEndpoint, search, genre, offset);
    }
}
