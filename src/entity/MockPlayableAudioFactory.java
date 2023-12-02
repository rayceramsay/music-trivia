package entity;

public class MockPlayableAudioFactory implements PlayableAudioFactory {

    @Override
    public PlayableAudio create(String audioPath) {
        return new MockPlayableAudio(audioPath);
    }
}
