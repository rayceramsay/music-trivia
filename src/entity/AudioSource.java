package entity;

import java.io.IOException;
import java.io.InputStream;

public interface AudioSource {
    InputStream getAudioStream() throws IOException;
}
