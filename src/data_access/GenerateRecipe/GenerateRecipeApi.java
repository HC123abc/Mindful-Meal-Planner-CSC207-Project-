package data_access.GenerateRecipe;

import okhttp3.*;

public class GenerateRecipeApi {

    public static String getRecipes(String apiKey, String tags, int number) {
        OkHttpClient client = new OkHttpClient();

        try {
            HttpUrl.Builder urlBuilder = HttpUrl.parse("https://api.spoonacular.com/recipes/random").newBuilder();
            urlBuilder.addQueryParameter("limitLicense", "true");
            urlBuilder.addQueryParameter("tags", tags);
            urlBuilder.addQueryParameter("number", String.valueOf(number));
            urlBuilder.addQueryParameter("apiKey", apiKey);

            String url = urlBuilder.build().toString();

            Request request = new Request.Builder()
                    .url(url)
                    .method("GET", null)
                    .build();

            Response response = client.newCall(request).execute();
            return response.body().string();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
