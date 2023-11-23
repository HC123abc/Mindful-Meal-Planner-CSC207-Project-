package use_case.favourites;

import data_access.InMemoryDataAccess.InMemoryDataAccessUser;
import data_access.InMemoryDataAccess.InMemoryDataAccessUserInterface;

import entity.Recipe;
import entity.RecipeFactory;
import entity.User;
import entity.FavouriteRecipes;

import java.net.URL;

import java.util.List;
import java.util.ArrayList;

import java.util.Map;
import java.util.HashMap;

public class FavouritesInteractor implements FavouritesInputBoundary {

    private FavouriteRecipes favouriteRecipes;
    private InMemoryDataAccessUserInterface inMemoryDataAccessUser;
    private User user;
    private FavouritesOutputBoundary favouritesPresenter;

    public FavouritesInteractor(InMemoryDataAccessUserInterface inMemoryDataAccessUser,
                                FavouritesOutputBoundary favouritesPresenter) {
        this.inMemoryDataAccessUser = inMemoryDataAccessUser;
        this.user = inMemoryDataAccessUser.getActiveUser();
        this.favouritesPresenter = favouritesPresenter;
    }

    @Override
    public void execute() {
        //TODO ADD IMPLEMENTATION, GETTING AND FORMATTING THE FAV RECIPES

        FavouriteRecipes favRecipes = user.getFavouriteRecipes();
        Map<Map<String, String>, String> recipes = turnRecipesIntoOutputData(favRecipes);
        FavouritesOutputData favouritesOutputData = new FavouritesOutputData(recipes); //TODO ADD ARGS

        this.favouritesPresenter.prepareSuccessView(favouritesOutputData);
    }

    private Map<Map<String,String>, String> turnRecipesIntoOutputData(FavouriteRecipes favRecipes) {
        // Initialize data structures
        ArrayList<Recipe> recipes = favRecipes.getFavouriteRecipes();
        Map<Map<String,String>, String> outputRecipes = new HashMap<>();

        // Loop through the list of Recipe objects
        for (int i = 0; i < recipes.size(); i++) {
            Recipe recipe = recipes.get(i);

            // Create a map for the current Recipe object's properties
            Map<String, String> recipeMap = new HashMap<>();
            recipeMap.put("title", recipe.getTitle());
            recipeMap.put("id", recipe.getId());
            recipeMap.put("readyInMinutes", recipe.getReadyInMinutes());
            recipeMap.put("servings", recipe.getServings());
            recipeMap.put("summary", recipe.getSummary());
            recipeMap.put("extendedIngredients", recipe.getExtendedIngredients());
            recipeMap.put("extendedInstructions", recipe.getExtendedInstructions());

            // create and add a KV pair with the current recipe's data as a Map key,
            // and it's image URL as a String value
            outputRecipes.put(recipeMap, recipe.getRecipeImageURL());
        }

        return outputRecipes;
    }

}
