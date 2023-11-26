package use_case.unfavouriteThis;

import data_access.InMemoryDataAccess.InMemoryDataAccessUserInterface;

import entity.User;
import entity.FavouriteRecipes;
import entity.Recipe;
import use_case.FavView.FavViewDataAccessInterface;

import java.util.HashMap;
import java.util.Map;


public class UnfavouriteThisInteractor implements UnfavouriteThisInputBoundary {

    private User user;
    private FavouriteRecipes favouriteRecipes;
    private UnfavouriteThisOutputBoundary unfavouriteThisPresenter;
    private InMemoryDataAccessUserInterface inMemoryDataAccessUser;
    private FavViewDataAccessInterface generateRecipeCardAPI;

    public UnfavouriteThisInteractor(FavViewDataAccessInterface generateRecipeCardAPI,
                                     InMemoryDataAccessUserInterface inMemoryDataAccessUser,
                                     UnfavouriteThisOutputBoundary unfavouriteThisPresenter) {

        this.generateRecipeCardAPI = generateRecipeCardAPI;
        this.inMemoryDataAccessUser = inMemoryDataAccessUser;
        this.unfavouriteThisPresenter = unfavouriteThisPresenter;
    }

    @Override
    public void execute(UnfavouriteThisInputData unfavouriteThisInputData) {
        this.user = inMemoryDataAccessUser.getActiveUser();
        this.favouriteRecipes = user.getFavouriteRecipes();

        String apiKey = "05e3b7688e8b4836a747fec4e0a6cdf7";

        // Entity manipulation to remove the specified recipe
        Recipe recipeToRemove = new Recipe(unfavouriteThisInputData.getTitle(),
                                    unfavouriteThisInputData.getReadyInMinutes(),
                                    unfavouriteThisInputData.getServings(),
                                    unfavouriteThisInputData.getSummary(),
                                    unfavouriteThisInputData.getExtendedIngredients(),
                                    unfavouriteThisInputData.getExtendedInstructions(),
                                    unfavouriteThisInputData.getRecipeImageURL(),
                                    unfavouriteThisInputData.getId());

        favouriteRecipes.removeOneRecipe(recipeToRemove);

//        // Output data object creation
//        String id = recipe.getId();
//        String cardImage = generateRecipeCardAPI.getRecipeCardUrl(apiKey, id);
//        if (cardImage == null) {
//            cardImage = recipe.getRecipeImageURL();
//        }
//
//        Map<String, String> recipeDetails = new HashMap<>();
//
//        recipeDetails.put("title", recipe.getTitle());
//        recipeDetails.put("readyInMinutes", recipe.getReadyInMinutes());
//        recipeDetails.put("servings", recipe.getServings());
//        recipeDetails.put("summary", recipe.getSummary());
//        recipeDetails.put("extendedIngredients", recipe.getExtendedIngredients());
//        recipeDetails.put("extendedInstructions", recipe.getExtendedInstructions());
//        recipeDetails.put("recipeImageURL", recipe.getRecipeImageURL());
//        recipeDetails.put("id", recipe.getId());
//
//        Map<String, Map<String, String>> recipeOutput = new HashMap<>();
//        recipeOutput.put(cardImage, recipeDetails);
//
//        UnfavouriteThisOutputData unfavouriteThisOutputData = new UnfavouriteThisOutputData(recipeOutput);
//        unfavouriteThisPresenter.prepareSuccessView(unfavouriteThisOutputData);



        Map<String, Map<String, String>> output = new HashMap<>(); // Create a single outputMap outside the loop
        if (favouriteRecipes.getFavouriteRecipes().isEmpty()) {
//            unfavouriteThisPresenter.prepareFailView("You have no favourite recipes now.");
            unfavouriteThisPresenter.prepareSuccessView(new UnfavouriteThisOutputData(output));
        }
        else {
            for (Recipe recipe : favouriteRecipes.getFavouriteRecipes()) {
                String id = recipe.getId();
                String cardImage = generateRecipeCardAPI.getRecipeCardUrl(apiKey, id);
                if (cardImage == null) {
                    cardImage = recipe.getRecipeImageURL();
                }
                Map<String, String> recipeDetails = toMap(recipe.getTitle(), recipe.getReadyInMinutes(), recipe.getServings(), recipe.getSummary(), recipe.getExtendedIngredients(), recipe.getExtendedInstructions(), recipe.getRecipeImageURL(), recipe.getId(), true);
                output.put(cardImage, recipeDetails);
            }

            UnfavouriteThisOutputData unfavouriteThisOutputData = new UnfavouriteThisOutputData(output);
            unfavouriteThisPresenter.prepareSuccessView(unfavouriteThisOutputData);
        }

    }


    public Map<String, String> toMap(String title, String readyInMinutes, String servings, String summary,
                                     String extendedIngredients, String extendedInstructions, String recipeImageURL,
                                     String id, boolean isFavourite) {

        Map<String, String> recipeDetailsMap = new HashMap<>();

        recipeDetailsMap.put("title", title);
        recipeDetailsMap.put("summary", summary);
        recipeDetailsMap.put("servings", servings);
        recipeDetailsMap.put("readyInMinutes", readyInMinutes);
        recipeDetailsMap.put("recipeImageURL", recipeImageURL);
        recipeDetailsMap.put("extendedIngredients", extendedIngredients);
        recipeDetailsMap.put("extendedInstructions", extendedInstructions);
        recipeDetailsMap.put("id", id);
        recipeDetailsMap.put("isFavourite", Boolean.toString(isFavourite));

        return recipeDetailsMap;
    }
}
