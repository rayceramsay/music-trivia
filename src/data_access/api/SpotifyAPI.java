package data_access.api;

import entity.Song;
import io.github.cdimascio.dotenv.Dotenv;
import okhttp3.*;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class SpotifyAPI{
    private final String CLIENT_ID;
    private final String CLIENT_SECRET;
    private final OkHttpClient client;
    private String authToken = null;
    private String genre = "hiphop";

    public SpotifyAPI(){
        Dotenv dotenv = Dotenv.configure().filename("api.env").load();
        this.CLIENT_ID = dotenv.get("CLIENT_ID");
        this.CLIENT_SECRET = dotenv.get("CLIENT_SECRET");
        this.client = new OkHttpClient().newBuilder().build();
    }
    public Song[] getSongs(String genre){
        this.genre = genre;
        return handleGetSongs();
    }
    public Song[] getSongs(){
        return handleGetSongs();
    }
    private void generateAuthToken(){
        String endpoint = "https://accounts.spotify.com/api/token";
        RequestBody body = new FormBody.Builder()
                .add("client_id", CLIENT_ID)
                .add("client_secret", CLIENT_SECRET)
                .add("grant_type", "client_credentials")
                .build();
        Request request = new Request.Builder()
                .url(endpoint)
                .addHeader("Content-Type", "application/x-www-form-urlencoded")
                .post(body)
                .build();
        try{
            Response response = client.newCall(request).execute();

            if(response.body() == null) {throw new RuntimeException("Error - no response body");}

            JSONObject responseBody = new JSONObject(response.body().string());
            authToken = responseBody.getString("access_token");

        } catch (IOException e){
            throw new RuntimeException(e);
        }
    }
    private JSONObject getSongData()  {
        String url = getRandomEndpoint();
        JSONObject response;
        try {
            Request request = new Request.Builder()
                .url(url)
                .addHeader("Authorization", "Bearer " + authToken)
                .addHeader("Content-Type", "application/json")
                .build();

            Response responseRequest = client.newCall(request).execute();

            if(responseRequest.code() == 401){ // Code 401 - bad (expired or null) auth token
                generateAuthToken();
                Request request2 = new Request.Builder()
                        .url(url)
                        .addHeader("Authorization", "Bearer " + authToken)
                        .addHeader("Content-Type", "application/json")
                        .build();
                responseRequest = client.newCall(request2).execute();
            }

            if(responseRequest.body() == null) {throw new RuntimeException("Error - no response body");}

            response = new JSONObject(responseRequest.body().string());

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return response;
    }
    private String getRandomEndpoint(){
        String base_endpoint = "https://api.spotify.com/v1/search?q=";
        char search = (char) ('a' + (new Random()).nextInt(26));
        return String.format("%s%s&genre:%s&type=track&limit=50", base_endpoint, search, genre);
    }
    private Song[] handleGetSongs(){

        ArrayList<Song> songs = new ArrayList<>();

        JSONObject responseBody = getSongData();
        JSONArray songsArray = responseBody.getJSONObject("tracks").getJSONArray("items");

        for (int i = 0; i < songsArray.length(); i++) {
            JSONObject item = (JSONObject) songsArray.get(i);
            JSONObject albumArtistInfo = (JSONObject) item.getJSONObject("album").getJSONArray("artists").get(0);
            if(item.get("preview_url") instanceof String && item.getInt("popularity") > 73){
                String songName = item.getString("name");
                String audio = item.getString("preview_url");
                String artistName = albumArtistInfo.getString("name");
                // TODO: Create Song entity
                // TODO: Add song to list
            }
        }
        return songs.toArray(Song[]::new);
    }



}
