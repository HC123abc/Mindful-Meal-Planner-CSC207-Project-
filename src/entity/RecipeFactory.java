package entity;

import org.json.JSONArray;
import org.json.JSONObject;

public class RecipeFactory {
    public Recipe create(JSONObject recipeObject) {

        String title = recipeObject.getString("title");
        int readyInMinutes = recipeObject.getInt("readyInMinutes");
        int servings = recipeObject.getInt("servings");
        String summary = recipeObject.getString("summary");
        String recipeImageURL = recipeObject.getString("image");

        // Extracting extendedIngredients
        JSONArray extendedIngredientsArray = recipeObject.getJSONArray("extendedIngredients");
        StringBuilder ingredientsBuilder = new StringBuilder();
        for (int j = 0; j < extendedIngredientsArray.length(); j++) {
            JSONObject ingredientObject = extendedIngredientsArray.getJSONObject(j);
            String ingredientName = ingredientObject.getString("name");
            double amount = ingredientObject.getDouble("amount");
            String unit = ingredientObject.getString("unit");
            String imageURL = buildIngredientImageURL(ingredientObject.getString("image"));

            ingredientsBuilder.append(imageURL).append(" ").append(ingredientName).append(": ").append(amount).append(" ").append(unit).append("\n");
        }
        String extendedIngredients = ingredientsBuilder.toString().trim();

        // Extracting extendedInstructions
        JSONArray analyzedInstructionsArray = recipeObject.getJSONArray("analyzedInstructions");
        StringBuilder instructionsBuilder = new StringBuilder();
        if (analyzedInstructionsArray.length() > 0) {
            JSONObject instructionsObject = analyzedInstructionsArray.getJSONObject(0);
            JSONArray stepsArray = instructionsObject.getJSONArray("steps");
            for (int j = 0; j < stepsArray.length(); j++) {
                JSONObject stepObject = stepsArray.getJSONObject(j);
                int stepNumber = stepObject.getInt("number");
                String stepDescription = stepObject.getString("step");
                instructionsBuilder.append(stepNumber).append(". ").append(stepDescription).append("\n");
            }
        }
        String extendedInstructions = instructionsBuilder.toString().trim();

        // Create and return the Recipe object
        return new Recipe(title, String.valueOf(readyInMinutes), String.valueOf(servings), summary, extendedIngredients, extendedInstructions, recipeImageURL);
    }
    private static String buildIngredientImageURL(String imageName) {
        // Assuming SIZE is 100x100, you can change it to the desired size
        String imageSize = "100x100";
        return "https://spoonacular.com/cdn/ingredients_" + imageSize + "/" + imageName;
    }
}





