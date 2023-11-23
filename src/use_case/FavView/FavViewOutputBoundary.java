package use_case.FavView;

import use_case.generateRecipe.GenerateRecipeOutputData;

public interface FavViewOutputBoundary {
    void prepareSuccessView(FavViewOutputData recipeOutputData);

    void prepareFailView(String error);

}
