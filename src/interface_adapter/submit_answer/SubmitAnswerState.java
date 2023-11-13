package interface_adapter.submit_answer;

public class SubmitAnswerState {
    private String correctnessTitle = "";
    private String correctnessMessage = "";

    public SubmitAnswerState(SubmitAnswerState copy) {
        correctnessTitle = copy.correctnessTitle;
        correctnessMessage = copy.correctnessMessage;
    }

    public SubmitAnswerState() {}

    public String getCorrectnessTitle() {
        return correctnessTitle;
    }

    public String getCorrectnessMessage() {
        return correctnessMessage;
    }

    public void setCorrectnessTitle(String correctnessTitle) {
        this.correctnessTitle = correctnessTitle;
    }

    public void setCorrectnessMessage(String correctnessMessage) {
        this.correctnessMessage = correctnessMessage;
    }
}