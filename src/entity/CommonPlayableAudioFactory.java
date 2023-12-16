package entity;

public class CommonPlayableAudioFactory implements PlayableAudioFactory {
    @Override
    public PlayableAudio create(String audioPath) {
        return new CommonPlayableAudio(audioPath);
    }
}
