package use_case.FavView;

import data_access.InMemoryDataAccess.InMemoryDataAccessUserInterface;
import entity.*;

import java.util.HashMap;
import java.util.Map;


public class FavViewInteractor implements FavViewInputBoundary {
    //    private GenerateRecipeOutputData generateRecipeOutputData;
    private FavViewDataAccessInterface generateRecipeCardAPI;
    private FavViewOutputBoundary favViewPresenter;
    private InMemoryDataAccessUserInterface inMemoryDataAccessUser;
    private User user;
    private FavouriteRecipes favouriteRecipes;


    public FavViewInteractor(FavViewDataAccessInterface generateRecipeCardAPI, FavViewOutputBoundary favViewPresenter, InMemoryDataAccessUserInterface inMemoryDataAccessUser) {
        this.generateRecipeCardAPI = generateRecipeCardAPI;
        this.inMemoryDataAccessUser = inMemoryDataAccessUser;
        this.favViewPresenter = favViewPresenter;

    }


    @Override
    public void execute() {
        this.user = inMemoryDataAccessUser.getActiveUser();
        this.favouriteRecipes = user.getFavouriteRecipes();
        String apiKey = "05e3b7688e8b4836a747fec4e0a6cdf7";

        Map<String, Map<String, String>> outputMap = new HashMap<>(); // Create a single outputMap outside the loop
        if (favouriteRecipes.getFavouriteRecipes().isEmpty()) {
            favViewPresenter.prepareFailView("You have no favourite recipes.  Add some!");
        }
        else{
        for (Recipe recipe : favouriteRecipes.getFavouriteRecipes()) {
            String id = recipe.getId();
            String cardImage = generateRecipeCardAPI.getRecipeCardUrl(apiKey, id);
            if (cardImage == null) {
                cardImage = recipe.getRecipeImageURL();
            }

            // Populate the outputMap with recipe details in each iteration
            Map<String, String> recipeDetailsMap = toMap(recipe.getTitle(), recipe.getReadyInMinutes(), recipe.getServings(), recipe.getSummary(), recipe.getExtendedIngredients(), recipe.getExtendedInstructions(), recipe.getRecipeImageURL(), recipe.getId(), true);
            outputMap.put(cardImage, recipeDetailsMap);

        }

        FavViewOutputData favViewOutputData = new FavViewOutputData(outputMap); // Create FavViewOutputData using the complete outputMap
        favViewPresenter.prepareSuccessView(favViewOutputData);
        }
    }



    // Constructor, getters, setters...

    public Map<String, String> toMap(String title, String readyInMinutes, String servings, String summary,
                                     String extendedIngredients, String extendedInstructions, String recipeImageURL,
                                     String id, boolean isFavourite) {
        Map<String, String> recipeDetailsMap = new HashMap<>();

        recipeDetailsMap.put("title", title);
        recipeDetailsMap.put("id", id);
        recipeDetailsMap.put("readyInMinutes", readyInMinutes);
        recipeDetailsMap.put("servings", servings);
        recipeDetailsMap.put("summary", summary);
        recipeDetailsMap.put("extendedIngredients", extendedIngredients);
        recipeDetailsMap.put("extendedInstructions", extendedInstructions);
        recipeDetailsMap.put("recipeImageURL", recipeImageURL);
        recipeDetailsMap.put("isFavourite", Boolean.toString(isFavourite));

        return recipeDetailsMap;
    }


}
