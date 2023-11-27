package use_case.Finish;


public class FinishInteractor implements FinishInputBoundary {

    private FinishOutputBoundary finishPresenter;

    public FinishInteractor(FinishOutputBoundary finishPresenter) {
        this.finishPresenter = finishPresenter;
    }

    @Override
    public void execute() {
        finishPresenter.prepareSuccessView();

    }
}
