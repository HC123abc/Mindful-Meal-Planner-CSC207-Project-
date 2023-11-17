package interface_adapter.CookThis;


import use_case.cookThis.CookThisInputBoundary;
import use_case.cookThis.CookThisInputData;

public class CookThisController {
    final CookThisInputBoundary cookThisInteractor;

    public CookThisController(CookThisInputBoundary cookThisInteractor) {
        this.cookThisInteractor = cookThisInteractor;
    }

    public void execute(String ingredients, String instruction) {
        CookThisInputData inputData = new CookThisInputData(instruction,ingredients);
        cookThisInteractor.execute(inputData);
    }
}

