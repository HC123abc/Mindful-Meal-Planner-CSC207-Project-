package entity;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class RecipeFactoryTest {

    @Test
    void createRecipeTest() {
        RecipeFactory recipeFactory = new RecipeFactory();

        JSONObject recipeObject = new JSONObject();
        recipeObject.put("title", "Test Recipe");
        recipeObject.put("readyInMinutes", 30);
        recipeObject.put("servings", 4);
        recipeObject.put("summary", "This is a test recipe summary.");
        recipeObject.put("image", "test_image.jpg");
        recipeObject.put("id", 0);

        JSONArray extendedIngredientsArray = new JSONArray();
        JSONObject ingredient1 = new JSONObject();
        ingredient1.put("name", "Ingredient A");
        ingredient1.put("amount", 100);
        ingredient1.put("unit", "g");
        ingredient1.put("image", "ingredientA.jpg");
        extendedIngredientsArray.put(ingredient1);

        JSONObject ingredient2 = new JSONObject();
        ingredient2.put("name", "Ingredient B");
        ingredient2.put("amount", 2);
        ingredient2.put("unit", "cups");
        ingredient2.put("image", "ingredientB.jpg");
        extendedIngredientsArray.put(ingredient2);

        recipeObject.put("extendedIngredients", extendedIngredientsArray);

        JSONArray analyzedInstructionsArray = new JSONArray();
        JSONObject instructionsObject = new JSONObject();
        JSONArray stepsArray = new JSONArray();
        JSONObject step1 = new JSONObject();
        step1.put("number", 1);
        step1.put("step", "Step 1: Do something");
        stepsArray.put(step1);
        instructionsObject.put("steps", stepsArray);
        analyzedInstructionsArray.put(instructionsObject);
        recipeObject.put("analyzedInstructions", analyzedInstructionsArray);

        Recipe recipe = recipeFactory.create(recipeObject);

        assertEquals("Test Recipe", recipe.getTitle());
        assertEquals("30", recipe.getReadyInMinutes());
        assertEquals("4", recipe.getServings());
        assertEquals("This is a test recipe summary.", recipe.getSummary());
        String expectedIngredients = "https://spoonacular.com/cdn/ingredients_100x100/ingredientA.jpg Ingredient A: 100.0 g\n" +
                "https://spoonacular.com/cdn/ingredients_100x100/ingredientB.jpg Ingredient B: 2.0 cups";
        assertEquals(expectedIngredients, recipe.getExtendedIngredients());
        String expectedInstructions = "1. Step 1: Do something";
        assertEquals(expectedInstructions, recipe.getExtendedInstructions());

        assertEquals("https://test_image.jpg", recipe.getRecipeImageURL());
        assertEquals("0", recipe.getId());
    }

}
