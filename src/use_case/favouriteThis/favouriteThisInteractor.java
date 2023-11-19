package use_case.favouriteThis;

import data_access.InMemoryDataAccess.InMemoryDataAccessUserInterface;
import entity.*;
import use_case.reRoll.ReRollOutputBoundary;

public class favouriteThisInteractor implements favouriteThisInputBoundary{
    private User user;
    private FavouriteRecipes favouriteRecipes;  // a FavouriteRecipes object
    private RecipeFactory recipeFactory;
    private favouriteThisOutputBoundary favouriteThisPresenter;

    public favouriteThisInteractor(InMemoryDataAccessUserInterface inMemoryDataAccessUser,
                                   favouriteThisOutputBoundary favouriteThisOB,
                                   RecipeFactory recipeFactory) {
        this.user = inMemoryDataAccessUser.getActiveUser();
        this.favouriteRecipes = user.getFavouriteRecipes();
        this.recipeFactory = recipeFactory;
        this.favouriteThisPresenter = favouriteThisOB;
    }

    private void execute(Recipe recipe) {

        favouriteRecipes.setFavouriteRecipes();
    }

    @Override
    public void execute() {

    }
}
