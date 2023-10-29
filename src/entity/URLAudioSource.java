package entity;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

public class URLAudioSource implements AudioSource {
    private final String audioUrlPath;

    public URLAudioSource(String audioUrlPath) {
        this.audioUrlPath = audioUrlPath;
    }

    @Override
    public InputStream getAudioStream() throws IOException {
        URL url = new URL(this.audioUrlPath);
        URLConnection connection = url.openConnection();
        return new BufferedInputStream(connection.getInputStream());
    }
}
