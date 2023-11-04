package entity;

public interface Round {

    // GETTERS
    String getQuestion();
    Song getSong();
    String getCorrectAnswer();
    String getUserAnswer();

    // SETTERS
    void setUserAnswer(String userAnswer);

    //OTHER
    boolean isAnswerCorrect(String answer);

}
