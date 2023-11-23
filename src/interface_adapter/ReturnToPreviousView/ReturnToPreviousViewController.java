package interface_adapter.ReturnToPreviousView;


import use_case.ReturnToPreviousView.ReturnToPreviousViewInputBoundary;
import use_case.ReturnToPreviousView.ReturnToPreviousViewInputData;

public class ReturnToPreviousViewController {

    final ReturnToPreviousViewInputBoundary returnToPreviousViewInputBoundary;

    public ReturnToPreviousViewController(ReturnToPreviousViewInputBoundary returnToPreviousViewInputBoundary) {
        this.returnToPreviousViewInputBoundary = returnToPreviousViewInputBoundary;
    }

    public void execute(String previousView) {
        ReturnToPreviousViewInputData inputData = new ReturnToPreviousViewInputData(previousView);
        returnToPreviousViewInputBoundary.execute(inputData);
    }
}
