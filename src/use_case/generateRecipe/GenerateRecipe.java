package use_case.generateRecipe;

import entity.User;

public class GenerateRecipe implements GenerateRecipeInputBoundary {
    //    private GenerateRecipeOutputData generateRecipeOutputData;
    private GenerateRecipeDataAccessInterface generateRecipeAPI;
    private GenerateRecipeOutputBoundary generateRecipeOutputBoundary;
    private User user;

    public GenerateRecipe(GenerateRecipeDataAccessInterface generateRecipeAPI, GenerateRecipeOutputBoundary generateRecipeOutputBoundary) {
        this.generateRecipeAPI = generateRecipeAPI;
        this.generateRecipeOutputBoundary = generateRecipeOutputBoundary;

    }


    @Override
    public void execute(GenerateRecipeInputData generateRecipeInputData) {
//  the input data will be formatted as a string so we can just add it to the query when we call the API call




    }
}
