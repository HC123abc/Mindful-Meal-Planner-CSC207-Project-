package interface_adapter.ReRoll;

import use_case.reRoll.ReRollInputBoundary;

public class ReRollController {

    final ReRollInputBoundary reRollInteractor;

    public ReRollController(ReRollInputBoundary reRollInteractor) {
        this.reRollInteractor = reRollInteractor;
    }

    public void execute() {
        reRollInteractor.execute();
    }
}
