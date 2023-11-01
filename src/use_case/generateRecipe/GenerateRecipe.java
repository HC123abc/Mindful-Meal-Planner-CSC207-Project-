package use_case.generateRecipe;

import entity.Preference;
import entity.User;

public class GenerateRecipe implements GenerateRecipeInputBoundary {
    //    private GenerateRecipeOutputData generateRecipeOutputData;
    private GenerateRecipeDataAccessInterface generateRecipeAPI;
    private GenerateRecipeOutputBoundary generateRecipeOutputBoundary;
    //    private User user;
    private Preference preference;

    public GenerateRecipe(GenerateRecipeDataAccessInterface generateRecipeAPI, GenerateRecipeOutputBoundary generateRecipeOutputBoundary, Preference preference) {
        this.generateRecipeAPI = generateRecipeAPI;
        this.generateRecipeOutputBoundary = generateRecipeOutputBoundary;
        this.preference = preference;

    }


    @Override
    public void execute() {
//  the input data will be formatted as a string so we can just add it to the query when we call the API call
        String tags = preference.getTags();



    }
}
