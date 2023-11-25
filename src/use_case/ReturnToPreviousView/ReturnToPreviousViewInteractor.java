package use_case.ReturnToPreviousView;




public class ReturnToPreviousViewInteractor implements ReturnToPreviousViewInputBoundary {

    private ReturnToPreviousViewOutputBoundary returnToPreviousViewPresenter;

    public ReturnToPreviousViewInteractor(ReturnToPreviousViewOutputBoundary returnToPreviousViewPresenter) {
        this.returnToPreviousViewPresenter = returnToPreviousViewPresenter;
    }

    @Override
    public void execute(ReturnToPreviousViewInputData inputData) {
        ReturnToPreviousViewOutputData outputData = new ReturnToPreviousViewOutputData(inputData.getPreviousView());
        returnToPreviousViewPresenter.prepareSuccessView(outputData);

    }
}
