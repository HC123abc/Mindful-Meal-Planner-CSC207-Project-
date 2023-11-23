package use_case.ReturnToPreviousView;

import java.util.Map;

public class ReturnToPreviousViewOutputData {

    private String previousView;
    public ReturnToPreviousViewOutputData(String previousView) {
        this.previousView = previousView;
    }
    public void setPreviousView(String previousView) {
        this.previousView = previousView;
    }
    public String getPreviousView() {
        return previousView;
    }

}
