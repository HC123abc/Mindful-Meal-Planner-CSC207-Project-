package data_access.GenerateRecipe;

import okhttp3.*;

import org.json.JSONObject; // Add this import for JSON parsing
import use_case.generateRecipe.GenerateRecipeDataAccessInterface;

public class GenerateRecipeApi implements GenerateRecipeDataAccessInterface {

    public JSONObject getRecipes(String apiKey, String tags, String intolerances, int number) {
        OkHttpClient client = new OkHttpClient();

        try {
            HttpUrl.Builder urlBuilder = HttpUrl.parse("https://api.spoonacular.com/recipes/random").newBuilder();
            urlBuilder.addQueryParameter("limitLicense", "true");
            urlBuilder.addQueryParameter("tags", tags);
            urlBuilder.addQueryParameter("intolerances", intolerances);
            urlBuilder.addQueryParameter("number", String.valueOf(number));
            urlBuilder.addQueryParameter("apiKey", apiKey);

            String url = urlBuilder.build().toString();

            Request request = new Request.Builder()
                    .url(url)
                    .method("GET", null)
                    .build();

            Response response = client.newCall(request).execute();

            // Check if the response is successful (status code 200)
            if (response.isSuccessful()) {
                // Convert the response body to a JSON object
                JSONObject jsonResponse = new JSONObject(response.body().string());
                return jsonResponse;
            } else {
                System.out.println("Error: " + response.code() + " - " + response.message());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

//  test
    public static void main(String[] args) {

    }
}
