package entity;

import java.util.List;

public class MockRoundFactory implements RoundFactory {

    @Override
    public Round createHardRound(String songGenre) {
        return new TextInputRound(createMockSong(), "Question", "answer");
    }

    @Override
    public Round createMediumRound(String songGenre) {
        MultipleChoiceRound multipleChoiceRound = new MultipleChoiceRound(createMockSong(), "Question", "answer");
        multipleChoiceRound.addIncorrectOptions(List.of(new String[]{"option1", "option2", "option3"}));

        return multipleChoiceRound;
    }

    @Override
    public Round createEasyRound(String songGenre) {
        MultipleChoiceRound multipleChoiceRound = new MultipleChoiceRound(createMockSong(), "Question", "answer");
        multipleChoiceRound.addIncorrectOptions(List.of(new String[]{"option1"}));

        return multipleChoiceRound;
    }

    private Song createMockSong() {
        return new CommonSong("title", "artist", new TestPlayableAudio("path.mp3"));
    }
}
