package entity;

public interface Round {

    // GETTERS
    String getQuestion();
    String getSong();
    String getCorrectAnswer();
    String getUserAnswer();

    // SETTERS
    void setUserAnswer(String userAnswer);

    //OTHER
    boolean isAnswerCorrect(String answer);

}
