package data_access.api;

import entity.Song;
import entity.SongFactory;
import okhttp3.*;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Random;

public class SpotifyAPI implements SongAPI{

    private final String clientId;
    private final String clientSecret;
    private final OkHttpClient client;
    private final SongFactory songFactory;
    private String authToken = null;

    public SpotifyAPI(SongFactory songFactory, String clientId, String clientSecret) {
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        this.client = new OkHttpClient().newBuilder().build();
        this.songFactory = songFactory;
    }

    @Override
    public Song getRandomSongFromGenre(String genre) {
        JSONObject responseBody = retrieveSongData(genre);
        JSONArray songsArray = responseBody.getJSONObject("tracks").getJSONArray("items");
        JSONObject item;
        Song song = null;
        int i = 0;
        do{
            item = (JSONObject) songsArray.get(i);
            if (item.get("preview_url") instanceof String){
                JSONObject albumArtistInfo = (JSONObject) item.getJSONObject("album").getJSONArray("artists").get(0);
                String songName = item.getString("name");
                String audio = item.getString("preview_url");
                String artistName = albumArtistInfo.getString("name");
                song = songFactory.create(songName, artistName, audio);
            }
            i++;
        }while (!(item.get("preview_url") instanceof String)  || item.getInt("popularity") < 83);

        if(song == null){
            return getRandomSongFromGenre(genre);
        }else {
            return song;
        }
    }
    private JSONObject retrieveSongData(String genre)  {
        String url = getRandomEndpoint(genre);
        JSONObject response;
        Request request = new Request.Builder()
                .url(url)
                .addHeader("Authorization", "Bearer " + authToken)
                .addHeader("Content-Type", "application/json")
                .build();
        try (Response responseRequest = client.newCall(request).execute()){
            if(responseRequest.code() == 401){ // Code 401 - bad (expired or null) auth token
                generateAuthToken();
                return retrieveSongData(genre); // recall api with auth token
            }
            if(responseRequest.body() == null) {throw new RuntimeException("Error - no response body");}
            response = new JSONObject(responseRequest.body().string());

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return response;
    }
    private void generateAuthToken(){
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
        try (Response response = client.newCall(request).execute()){
            if(response.body() == null) {throw new RuntimeException("Error - no response body");}

            JSONObject responseBody = new JSONObject(response.body().string());
            authToken = responseBody.getString("access_token");

        } catch (IOException e){
            throw new RuntimeException(e);
        }
    }
    private String getRandomEndpoint(String genre){
        String base_endpoint = "https://api.spotify.com/v1/search?q=";
        char search = (char) ('a' + (new Random()).nextInt(26));
        return String.format("%s%s&genre:%s&type=track&limit=50", base_endpoint, search, genre);
    }
}
