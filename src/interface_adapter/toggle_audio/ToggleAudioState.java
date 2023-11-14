package interface_adapter.toggle_audio;

public class ToggleAudioState {
    private String imgPath = "";

    public ToggleAudioState(ToggleAudioState copy) {
        imgPath = copy.imgPath;
    }

    public ToggleAudioState() {}

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }
}
