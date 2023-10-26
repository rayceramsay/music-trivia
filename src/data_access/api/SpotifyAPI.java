package data_access.api;

import data_access.FileAPIDataAccessObject;
import okhttp3.*;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

/**
 Example usage:
 SpotifyAPI sapi = new SpotifyAPI();
 ArrayList<HashMap> songs = sapi.getSongs();
 for(HashMap s: songs){
    System.out.println(s);
 }
 **/
public class SpotifyAPI{
    private final FileAPIDataAccessObject APIDATA;
    private final String GET_SEARCH_BASE_ENDPOINT;
    private final String POST_AUTH_TOKEN_ENDPOINT;


    public SpotifyAPI(){
        this.GET_SEARCH_BASE_ENDPOINT = "https://api.spotify.com/v1/search?q=";
        this.POST_AUTH_TOKEN_ENDPOINT = "https://accounts.spotify.com/api/token";
        try {
            this.APIDATA = new FileAPIDataAccessObject("./spotify_api.csv");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public ArrayList<HashMap> getSongs(String genre){
        return getSongsCall(genre);
    }
    public ArrayList<HashMap> getSongs(){
        return getSongsCall("hiphop");
    }
    private ArrayList<HashMap> getSongsCall(String genre){
        if(this.APIDATA.expiredToken()){
            generateAuthToken();
        }
        Random r = new Random();
        char search = (char) ('a' + r.nextInt(26));
        String url = String.format("%s%s&genre:%s&type=track&limit=50",GET_SEARCH_BASE_ENDPOINT,search,genre);
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        Request request = new Request.Builder()
                .url(url)
                .addHeader("Authorization", "Bearer " + APIDATA.getAuthToken())
                .addHeader("Content-Type", "application/json")
                .build();
        ArrayList<HashMap> songs = new ArrayList<>();
        try {
            Response response = client.newCall(request).execute();

            if (response.body() != null) {
                JSONObject responseBody = new JSONObject(response.body().string());
                JSONArray songsArray = responseBody.getJSONObject("tracks").getJSONArray("items");
                for (int i = 0; i < songsArray.length(); i++) {
                    JSONObject item = (JSONObject) songsArray.get(i);
                    JSONObject albumArtistInfo = (JSONObject) item.getJSONObject("album").getJSONArray("artists").get(0);
                    if(item.get("preview_url") instanceof String && item.getInt("popularity") > 73){
                        HashMap<String, String> song = new HashMap<>();
                        song.put("name", item.getString("name"));
                        song.put("audio", item.getString("preview_url"));
                        song.put("artist", albumArtistInfo.getString("name"));
                        songs.add(song);
                    }
                }
            } else {
                System.out.println("No response body :(");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return songs;
    }


    private void generateAuthToken(){
        System.out.println("GENERATING NEW TOKEN");
        OkHttpClient client = new OkHttpClient().newBuilder().build();
        RequestBody body = new FormBody.Builder()
                .add("client_id", APIDATA.getClientId())
                .add("client_secret", APIDATA.getClientSecret())
                .add("grant_type", "client_credentials")
                .build();
        Request request = new Request.Builder()
                .url(POST_AUTH_TOKEN_ENDPOINT)
                .addHeader("Content-Type", "application/x-www-form-urlencoded")
                .post(body)
                .build();
        try{
            Response response = client.newCall(request).execute();
            if (response.body() != null) {
                JSONObject responseBody = new JSONObject(response.body().string());
                APIDATA.setNewToken(responseBody.getString("access_token"));
            }else{
                System.out.println("No response body");
            }
        } catch (IOException e){
            throw new RuntimeException(e);
        }
    }
}
