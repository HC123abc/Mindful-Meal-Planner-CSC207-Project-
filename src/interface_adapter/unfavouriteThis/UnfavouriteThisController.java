package interface_adapter.unfavouriteThis;

import use_case.unfavouriteThis.UnfavouriteThisInputBoundary;
import use_case.unfavouriteThis.UnfavouriteThisInputData;

public class UnfavouriteThisController {
    final UnfavouriteThisInputBoundary unfavouriteThisInteractor;

    public UnfavouriteThisController(UnfavouriteThisInputBoundary unfavouriteThisInteractor) {
        this.unfavouriteThisInteractor = unfavouriteThisInteractor;
    }

    public void execute(String title, String summary, String recipeImageURL,
                               String servings, String readyInMinutes, String extendedIngredients,
                               String extendedInstructions, String id) {

            UnfavouriteThisInputData unfavouriteThisInputData = new UnfavouriteThisInputData(title,
                                                                            summary,
                                                                            recipeImageURL,
                                                                            servings,
                                                                            readyInMinutes,
                                                                            extendedIngredients,
                                                                            extendedInstructions,
                                                                            id);

            unfavouriteThisInteractor.execute(unfavouriteThisInputData);
    }
}
