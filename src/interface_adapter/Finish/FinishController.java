package interface_adapter.Finish;

import use_case.Finish.FinishInputBoundary;

public class FinishController {

    final FinishInputBoundary finsihInteractor;

    public FinishController(FinishInputBoundary finsihInteractor) {
        this.finsihInteractor = finsihInteractor;
    }

    public void execute() {
        finsihInteractor.execute();
    }
}
