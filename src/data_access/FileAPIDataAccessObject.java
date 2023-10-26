package data_access;

import data_access.api.SpotifyAPIDataAccessInterface;

import java.io.*;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

public class FileAPIDataAccessObject implements SpotifyAPIDataAccessInterface {
    private final File csvFile;
    private final Map<String, Integer> headers = new LinkedHashMap<>();
    private String authToken;
    private LocalDateTime authLastCreated;
    private String clientId;
    private String clientSecret;

    public FileAPIDataAccessObject(String csvPath) throws IOException {
        csvFile = new File(csvPath);
        headers.put("creation_time", 0);
        headers.put("auth_token", 1);
        headers.put("client_id", 2);
        headers.put("client_secret", 3);

        if(this.csvFile.length() == 0){
            save(); // create file but still need to manually add client id and secret for program to run
        }else{
            try (BufferedReader reader = new BufferedReader(new FileReader(csvFile))) {
                String header = reader.readLine();

                assert header.equals("creation_time,auth_token,client_id,client_secret");

                String row =  reader.readLine();

                assert row != null; // should always contain client_id and cleint_secret

                String[] col = row.split(",");
                clientId = String.valueOf(col[headers.get("client_id")]);
                clientSecret = String.valueOf(col[headers.get("client_secret")]);
                String authText = String.valueOf(col[headers.get("auth_token")]);
                if(!Objects.equals(authText, "null")) {
                    authToken = authText;
                }
                String creationTimeText = String.valueOf(col[headers.get("creation_time")]);
                if(!Objects.equals(creationTimeText, "null")){
                    authLastCreated = LocalDateTime.parse(creationTimeText);
                }
            }
        }
    }
    private void save(){
        BufferedWriter writer;
        try {
            writer = new BufferedWriter(new FileWriter(csvFile));
            writer.write(String.join(",", headers.keySet()));
            writer.newLine();
            String line = String.format("%s,%s,%s,%s",authLastCreated,authToken,clientId,clientSecret);
            writer.write(line);
            writer.newLine();
            writer.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public String getAuthToken(){
        return this.authToken;
    }
    public String getClientId(){
        return this.clientId;
    }
    public String getClientSecret(){
        return this.clientSecret;
    }
    @Override
    public boolean expiredToken() {
        LocalDateTime now = LocalDateTime.now();
        if(authToken == null) return true;
        return authLastCreated.plusSeconds(3600).isBefore(now);
    }
    @Override
    public void setNewToken(String token) {
        authLastCreated = LocalDateTime.now();
        authToken = token;
        this.save();
    }


}
