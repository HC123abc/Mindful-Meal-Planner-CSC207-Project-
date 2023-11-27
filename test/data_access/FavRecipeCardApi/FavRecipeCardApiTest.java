package data_access.FavRecipeCardApi;

import static org.junit.jupiter.api.Assertions.*;

import okhttp3.*;
import org.json.JSONObject;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class FavRecipeCardApiTest {

    private static final String VALID_API_KEY = "05e3b7688e8b4836a747fec4e0a6cdf7";
    private static final String VALID_RECIPE_ID = "1234";
    private static final String INVALID_API_KEY = null; // Set to null to simulate failure

    @Test
    public void testGetRecipeCardUrl_Success() {
        FavRecipeCardApi api = new FavRecipeCardApi();
        String recipeCardUrl = api.getRecipeCardUrl(VALID_API_KEY, VALID_RECIPE_ID);

        assertNotNull(recipeCardUrl);
    }

    @Test
    public void testGetRecipeCardUrl_Failure() {
        FavRecipeCardApi api = new FavRecipeCardApi();
        String recipeCardUrl = api.getRecipeCardUrl(INVALID_API_KEY, VALID_RECIPE_ID);

        assertNull(recipeCardUrl);
    }


}