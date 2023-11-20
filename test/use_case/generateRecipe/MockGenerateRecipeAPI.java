package use_case.generateRecipe;

import org.json.JSONArray;
import org.json.JSONObject;

class MockGenerateRecipeAPI implements GenerateRecipeDataAccessInterface {
    @Override
//  simulate the json that the api call returns
    public JSONObject getRecipes(String apiKey, String tags, String intolerances, int count) {
        // Simulate a response JSON for successful scenario
        JSONObject response = new JSONObject();

        JSONArray recipesArray = new JSONArray();
        for (int i = 0; i < count; i++) {
            JSONObject recipeObject = new JSONObject();
            recipeObject.put("title", "Harley Good Recipe " + (i + 1));
            recipeObject.put("readyInMinutes", 30 + i * 5);
            recipeObject.put("servings", 2 + i);
            recipeObject.put("summary", "This is a wonderful recipe with fantastic flavors. <b>Enjoy!</b>");
            recipeObject.put("image", "https://example.com/recipe_" + (i + 1) + ".jpg");
            recipeObject.put("id",  (i + 1));

            // Simulate extended ingredients
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

            // Simulate extended instructions
            JSONArray analyzedInstructionsArray = new JSONArray();
            JSONObject instructionObject = new JSONObject();
            JSONArray stepsArray = new JSONArray();
            for (int j = 1; j <= 3; j++) {
                JSONObject step = new JSONObject();
                step.put("number", j);
                step.put("step", "Step " + j + ": Do something for recipe " + (i + 1));
                stepsArray.put(step);
            }
            instructionObject.put("steps", stepsArray);
            analyzedInstructionsArray.put(instructionObject);
            recipeObject.put("analyzedInstructions", analyzedInstructionsArray);

            recipesArray.put(recipeObject);
        }

        response.put("recipes", recipesArray);
        return response;
    }

}