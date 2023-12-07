package use_case.unfavouriteThis;

import data_access.InMemoryDataAccess.InMemoryDataAccessUserInterface;

import entity.User;
import entity.FavouriteRecipes;
import entity.Recipe;
import org.jetbrains.annotations.NotNull;
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


        // Entity manipulation to remove the specified recipe
        Recipe recipeToRemove = getRecipeToRemove(unfavouriteThisInputData);

        // Removing the recipe from the user's favouriteRecipes entity
        favouriteRecipes.removeOneRecipe(recipeToRemove);

        // Removing the recipe from the outputData data structure
        Map<String, Map<String, String>> output = unfavouriteThisInputData.getRecipes();
        String entryToRemoveKey = unfavouriteThisInputData.getEntryKey();
        Map<String, String> removedValue = output.remove(entryToRemoveKey);


        UnfavouriteThisOutputData unfavouriteThisOutputData = new UnfavouriteThisOutputData(output);
        unfavouriteThisPresenter.prepareSuccessView(unfavouriteThisOutputData);

    }

    @NotNull
    private static Recipe getRecipeToRemove(UnfavouriteThisInputData unfavouriteThisInputData) {
        Map<String, String> entry = unfavouriteThisInputData.getEntryValue();
        Recipe recipeToRemove = new Recipe(entry.get("title"),entry.get("readyInMinutes"),
                                            entry.get("servings"),
                                            entry.get("summary"),
                                            entry.get("extendedIngredients"),
                                            entry.get("extendedInstructions"),
                                            entry.get("recipeImageURL"),
                                            entry.get("id"));
        return recipeToRemove;
    }

}
