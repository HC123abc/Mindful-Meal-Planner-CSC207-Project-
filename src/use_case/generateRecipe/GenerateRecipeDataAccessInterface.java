package use_case.generateRecipe;

import use_case.generateRecipe.GenerateRecipeInputData;

import java.util.List;

public interface GenerateRecipeDataAccessInterface {
    public List<Object> getRandomRecipeList(GenerateRecipeInputData generateRecipeInputData);
}
