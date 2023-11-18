package data_access.GenerateRecipe;

import entity.Recipe;
import entity.RecipeFactory;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class GenerateRecipeApiTest {

    @Test
    void testGetRecipesAndFactorySuccess() {
        String apiKey = "d6d8b743e3fd4afeac18d54cef0e21ff";
        String tags = "chinese";
        int number = 1;

        GenerateRecipeApi api = new GenerateRecipeApi();
        JSONObject response = api.getRecipes(apiKey, tags, number);

        assertNotNull(response);

        RecipeFactory recipeFactory = new RecipeFactory();
        Recipe recipe = recipeFactory.create(response.getJSONArray("recipes").getJSONObject(0));

        assertNotNull(recipe);
        String title = response.getJSONArray("recipes").getJSONObject(0).getString("title");
        int readyInMinutes = response.getJSONArray("recipes").getJSONObject(0).getInt("readyInMinutes");
        int servings = response.getJSONArray("recipes").getJSONObject(0).getInt("servings");

        assertEquals(title, recipe.getTitle());
        assertEquals(String.valueOf(readyInMinutes), recipe.getReadyInMinutes());
        assertEquals(String.valueOf(servings), recipe.getServings());
    }

    @Test
    void testGetRecipesAndFactoryFailure() {
        String apiKey = null; // Set API key as null to simulate failure
        String tags = "chinese";
        int number = 10;

        GenerateRecipeApi api = new GenerateRecipeApi();
        JSONObject response = api.getRecipes(apiKey, tags, number);

        assertNull(response);
    }
}
