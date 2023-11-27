package interface_adapter.unfavouriteThis;

import use_case.unfavouriteThis.UnfavouriteThisInputBoundary;
import use_case.unfavouriteThis.UnfavouriteThisInputData;

import java.util.Map;

public class UnfavouriteThisController {
    final UnfavouriteThisInputBoundary unfavouriteThisInteractor;

    public UnfavouriteThisController(UnfavouriteThisInputBoundary unfavouriteThisInteractor) {
        this.unfavouriteThisInteractor = unfavouriteThisInteractor;
    }


    public void execute(Map<String, Map<String, String>> recipes, Map<String, Map<String, String>> entry) {

        UnfavouriteThisInputData unfavouriteThisInputData = new UnfavouriteThisInputData(recipes, entry);

        unfavouriteThisInteractor.execute(unfavouriteThisInputData);
    }
}
