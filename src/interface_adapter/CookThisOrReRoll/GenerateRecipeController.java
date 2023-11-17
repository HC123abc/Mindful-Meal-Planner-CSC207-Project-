package interface_adapter.CookThisOrReRoll;

import use_case.generateRecipe.GenerateRecipeInputBoundary;
import use_case.reRoll.ReRollInputBoundary;

public class GenerateRecipeController {

    final GenerateRecipeInputBoundary generateRecipeInteractor;

    public GenerateRecipeController(GenerateRecipeInputBoundary generateRecipeInteractor) {
        this.generateRecipeInteractor = generateRecipeInteractor;
    }

    public void execute() {
        generateRecipeInteractor.execute();
    }
}
