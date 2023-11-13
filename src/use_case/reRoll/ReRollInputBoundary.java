package use_case.reRoll;

import entity.Preference;
import entity.RandomRecipe;
import entity.RecipeFactory;
import use_case.generateRecipe.GenerateRecipeDataAccessInterface;
import use_case.generateRecipe.GenerateRecipeOutputBoundary;

public interface ReRollInputBoundary {

    public void execute();

}
