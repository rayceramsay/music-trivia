package entity;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class FileAudioSource implements AudioSource {
    private final String audioFilePath;

    public FileAudioSource(String audioFilePath) {
        this.audioFilePath = audioFilePath;
    }

    @Override
    public InputStream getAudioStream() throws IOException {
        FileInputStream fileInputStream = new FileInputStream(this.audioFilePath);
        return new BufferedInputStream(fileInputStream);
    }
}
