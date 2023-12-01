package entity;

import java.util.ArrayList;

/**
 * Interface for Round
 */
public interface Round {
    /**
     * @return Prompt of round.
     */
    String getQuestion();

    /**
     * @return Song of round.
     */
    Song getSong();

    /**
     * @return Correct answer of round.
     */
    String getCorrectAnswer();

    /**
     * @return User answer of round.
     */
    String getUserAnswer();

    /**
     * @param userAnswer answer given by user
     */
    void setUserAnswer(String userAnswer);

    /**
     * @return if answer given by user is correct.
     */
    boolean isUserAnswerCorrect();

    /**
     * @return if round is finished.
     */
    boolean isFinished();

    /**
     * @return List of all multiple choice answers
     */
    ArrayList<String> getMultipleChoiceAnswers();
}
