/*
This was supposed to be Takuma's work however we haven't been able to reach him.
He was not updating us on his progress.

We need logout to be implemented for testing proposes and just to finish the whole
application so I (Harley) implemented logout.

Since this is just for testing proposes, it was rushed. It follows clean architecture
but I cut some corners. Moreover, there are no test for this use case.
 */

package use_case.logout;

import data_access.InMemoryDataAccess.InMemoryDataAccessUserInterface;
import entity.FavouriteRecipes;
import entity.Preference;
import entity.Recipe;
import entity.User;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class LogoutInteractor implements LogoutInputBoundary {
    private InMemoryDataAccessUserInterface inMemoryDataAccessUser;
    private LogoutOutputBoundary logoutPreseneter;

    public LogoutInteractor(InMemoryDataAccessUserInterface inMemoryDataAccessUser, LogoutOutputBoundary logoutPreseneter) {
        this.inMemoryDataAccessUser = inMemoryDataAccessUser;
        this.logoutPreseneter = logoutPreseneter;
    }

    @Override
    public void execute() {
        User user = inMemoryDataAccessUser.getActiveUser();
        saveUserDataToJson(user);
        logoutPreseneter.prepareSuccessView();
    }

    private void saveUserDataToJson(User user) {
        JSONObject userData = new JSONObject();
        userData.put("username", user.getUsername());
        userData.put("password", user.getPassword());

        // Favourite Recipes
        JSONObject favouriteRecipes = new JSONObject();
        FavouriteRecipes userFavRecipes = user.getFavouriteRecipes();
        JSONArray favRecipesArray = new JSONArray();

        List<Recipe> recipes = userFavRecipes.getFavouriteRecipes();
        for (int i = 0; i < recipes.size(); i++) {
            Recipe recipe = recipes.get(i);
            JSONObject recipeObj = new JSONObject();
            recipeObj.put("title", recipe.getTitle());
            recipeObj.put("readyInMinutes", recipe.getReadyInMinutes());
            recipeObj.put("servings", recipe.getServings());
            recipeObj.put("summary", recipe.getSummary());
            recipeObj.put("extendedIngredients", recipe.getExtendedIngredients());
            recipeObj.put("extendedInstructions", recipe.getExtendedInstructions());
            recipeObj.put("recipeImageURL", recipe.getRecipeImageURL());
            recipeObj.put("id", recipe.getId());
            favRecipesArray.add(recipeObj);
        }

        favouriteRecipes.put("favouriteRecipes", favRecipesArray);
        userData.put("favouriteRecipes", favouriteRecipes);


        // Preferences
        JSONObject preference = new JSONObject();
        Preference userPref = user.getPreference();
        JSONArray cuisinesArray = new JSONArray();
        cuisinesArray.addAll(userPref.getSelectedCuisines());
        JSONArray dietsArray = new JSONArray();
        dietsArray.addAll(userPref.getSelectedDiets());
        JSONArray intolerancesArray = new JSONArray();
        intolerancesArray.addAll(userPref.getSelectedIntolerances());
        preference.put("selectedCuisines", cuisinesArray);
        preference.put("selectedDiets", dietsArray);
        preference.put("selectedIntolerances", intolerancesArray);
        userData.put("preference", preference);

        // Random Recipe
        JSONObject randomRecipe = new JSONObject();
        randomRecipe.put("currentRecipeIndex", 0);
        userData.put("randomRecipe", randomRecipe);

        String jsonUserData = userData.toJSONString();

        String fileName = user.getUsername() + ".json";
        String filePath = "users/" + fileName; // Path to the users folder

        try (FileWriter fileWriter = new FileWriter(filePath)) {
            fileWriter.write(jsonUserData);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
