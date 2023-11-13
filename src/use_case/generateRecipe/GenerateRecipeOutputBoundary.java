package use_case.generateRecipe;

public interface GenerateRecipeOutputBoundary {
    void prepareSuccessView(GenerateRecipeOutputData recipeOutputData);

    void prepareFailView(String error);

}
