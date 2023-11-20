package use_case.generateRecipe;

import org.json.JSONObject;
import use_case.generateRecipe.GenerateRecipeInputData;

import java.util.List;

public interface GenerateRecipeDataAccessInterface {
    public JSONObject getRecipes(String apiKey, String tags, String intolerances, int number);
}
