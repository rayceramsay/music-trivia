package entity;

import java.util.ArrayList;
import java.util.List;

public class MockRoundFactory implements RoundFactory {

    @Override
    public Round createBasicRound(Song song, String question, String correctAnswer, String userAnswer) {
        return new BasicRound(song, question, correctAnswer, userAnswer);
    }

    @Override
    public OptionRound createOptionRound(Song song, String question, String correctAnswer, String userAnswer, List<String> incorrectOptions) {
        return new MultipleChoiceRound(song, question, correctAnswer, userAnswer, incorrectOptions);
    }

    @Override
    public Round generateBasicRoundFromGenre(String songGenre) {
        return new BasicRound(createMockSong(), "Question", "answer");
    }

    @Override
    public OptionRound generateOptionRoundFromGenre(String songGenre, int incorrectOptionsCount) {
        List<String> incorrectOptions = new ArrayList<>();
        for (int i = 0; i < incorrectOptionsCount; i++) {
            incorrectOptions.add("option" + i);
        }

        return new MultipleChoiceRound(createMockSong(), "Question", "answer", incorrectOptions);
    }

    private Song createMockSong() {
        return new CommonSong("title", "artist", new MockPlayableAudio("path.mp3"));
    }
}
