package entity;

import java.util.List;

public class MockRoundFactory implements RoundFactory {

    @Override
    public Round createHardRound(String songGenre) {
        return new BasicRound(createMockSong(), "Question", "answer");
    }

    @Override
    public Round createMediumRound(String songGenre) {
        List<String> incorrectOptions = List.of(new String[]{"option1", "option2", "option3"});

        return new MultipleChoiceRound(createMockSong(), "Question", "answer", incorrectOptions);
    }

    @Override
    public Round createEasyRound(String songGenre) {
        List<String> incorrectOptions = List.of(new String[]{"option1"});

        return new MultipleChoiceRound(createMockSong(), "Question", "answer", incorrectOptions);
    }

    private Song createMockSong() {
        return new CommonSong("title", "artist", new TestPlayableAudio("path.mp3"));
    }
}
