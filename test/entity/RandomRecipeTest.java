package entity;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RandomRecipeTest {

    private RandomRecipe randomRecipe;

    @BeforeEach
    void setUp() {
        randomRecipe = new RandomRecipe();
        JSONArray recipes = new JSONArray();
        for (int i = 0; i < 3; i++) {
            JSONObject recipeObject = new JSONObject();
            recipeObject.put("title", "Recipe " + (i + 1));
            recipes.put(recipeObject);
        }
        randomRecipe.setRandomRecipeList(recipes);
    }

    @Test
    void testUpdateRecipeIndex() {
        assertEquals(0, randomRecipe.getCurrentRecipeIndex());
        randomRecipe.setCurrentRecipeIndex(1);
        assertEquals(1, randomRecipe.getCurrentRecipeIndex());
        randomRecipe.setCurrentRecipeIndex(2);
        assertEquals(2, randomRecipe.getCurrentRecipeIndex());
        // Test loop back to the first recipe after reaching the end
        randomRecipe.setCurrentRecipeIndex(3);
        assertEquals(3, randomRecipe.getCurrentRecipeIndex());
    }
    @Test
    void testGetRandomRecipeList() {
        JSONArray expectedRecipes = new JSONArray();
        for (int i = 0; i < 3; i++) {
            JSONObject recipeObject = new JSONObject();
            recipeObject.put("title", "Recipe " + (i + 1));
            expectedRecipes.put(recipeObject);
        }
        assertEquals(expectedRecipes.toString(), randomRecipe.getRandomRecipeList().toString());
    }
}

