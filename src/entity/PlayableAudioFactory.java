package entity;

public interface PlayableAudioFactory {
    /**
     * @param audioPath url of the song audio
     * @return PlayableAudio
     */
    PlayableAudio create(String audioPath);
}
