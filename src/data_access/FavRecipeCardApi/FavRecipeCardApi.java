package data_access.FavRecipeCardApi;

import okhttp3.*;
import org.json.JSONObject;
import use_case.FavView.FavViewDataAccessInterface;

import java.util.concurrent.TimeUnit;

public class FavRecipeCardApi implements FavViewDataAccessInterface {

    public String getRecipeCardUrl(String apiKey, String id) {
        OkHttpClient client = new OkHttpClient();

        try {
            TimeUnit.SECONDS.sleep(1); // wait 1 sec for free API token as they only allow 1 per second, without i get errors

            String url = "https://api.spoonacular.com/recipes/" + id + "/card?apiKey=" + apiKey;

            Request request = new Request.Builder()
                    .url(url)
                    .method("GET", null)
                    .build();

            Response response = client.newCall(request).execute();
            if (response.isSuccessful()) {
                String responseBody = response.body().string();
                JSONObject jsonResponse = new JSONObject(responseBody);
                String recipeCardUrl = jsonResponse.optString("url");
                if (recipeCardUrl != null && !recipeCardUrl.isEmpty()) {
                    return recipeCardUrl; // Found recipe card URL
                }
            } else {
                System.out.println("Error: " + response.code() + " - " + response.message());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // If fetching the recipe card by ID fails or returns null, try creating one using provided inputs
        return null;
    }


    public static void main(String[] args) {
        String apiKey = "05e3b7688e8b4836a747fec4e0a6cdf7"; // Replace with your API key
        String recipeId = "";
        String title = "Your Recipe Title";
        String readyInMinutes = "30";
        String servings = "4";
        String summary = "Recipe summary";
        String extendedIngredients = "Ingredient 1\nIngredient 2";
        String extendedInstructions = "Step 1\nStep 2";
        String recipeImageURL = "https://yourrecipeimage.com/image.jpg";

        FavRecipeCardApi api = new FavRecipeCardApi();
        String recipeCardUrl = api.getRecipeCardUrl(apiKey, recipeId);

        if (recipeCardUrl != null) {
            System.out.println("Recipe Card URL: " + recipeCardUrl);
        } else {
            System.out.println("Unable to fetch or create recipe card");
        }
    }
}
