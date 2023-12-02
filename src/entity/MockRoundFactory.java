package entity;

import java.util.ArrayList;
import java.util.List;

public class MockRoundFactory implements RoundFactory {

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
        return new CommonSong("title", "artist", new TestPlayableAudio("path.mp3"));
    }
}
