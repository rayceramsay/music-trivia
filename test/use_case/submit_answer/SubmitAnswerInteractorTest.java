package use_case.submit_answer;

import org.junit.Test;

import static org.junit.Assert.*;

class SubmitAnswerInteractorTest {
    // TODO write tests ensuring that the submit answer interactor behaves as expected
    //     - check in the following scenarios that:
    //     a) the game gets updated properly
    //     b) the presenter receives the correct output
    //         - correct user answer was given and there is another round to be played
    //         - correct user answer was given and the game is over
    //         - incorrect user answer was given and there is another round to be played
    //         - incorrect user answer was given and the game is over
    //     - check that the presenter receives the official correct answer

    // QUESTION: Is it better to have just single `prepareView` method in output boundary instead
    // of `prepareCorrectView` and `prepareIncorrectView` since we care about both correctness of
    // round and game over status?
}
