package use_case.ReturnToPreviousView;

public class ReturnToPreviousViewInputData {

    private String previousView;
    public ReturnToPreviousViewInputData(String previousView) {
        this.previousView = previousView;
    }

    public void setPreviousView(String previousView) {
        this.previousView = previousView;
    }
    public String getPreviousView() {
        return previousView;
    }
}
