package use_case.generateRecipe;

import data_access.generateRecipe.GenerateRecipeDataAccessInterface;
import entity.User;
import interface_adapter.generateRecipe.GenerateRecipeOutputBoundary;


import java.time.LocalDateTime;

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


    }
}
