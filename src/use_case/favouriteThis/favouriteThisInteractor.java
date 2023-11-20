package use_case.favouriteThis;

import data_access.InMemoryDataAccess.InMemoryDataAccessUserInterface;
import entity.*;
import use_case.reRoll.ReRollOutputBoundary;

public class favouriteThisInteractor implements favouriteThisInputBoundary{
    private User user;
    private FavouriteRecipes favouriteRecipes;  // a FavouriteRecipes object
    private RecipeFactory recipeFactory;
    private favouriteThisOutputBoundary fTPresenter;

    public favouriteThisInteractor(InMemoryDataAccessUserInterface inMemoryDataAccessUser,
                                   favouriteThisOutputBoundary fTPresenter) {
        this.user = inMemoryDataAccessUser.getActiveUser();
        this.favouriteRecipes = user.getFavouriteRecipes();
        this.fTPresenter = fTPresenter;
    }

//    private void execute(Recipe recipe) {
//
//        favouriteRecipes.setFavouriteRecipes();
//    }
//
//    @Override
//    public void execute() {
//
//    }

    @Override
    public void execute(favouriteThisInputData fTInputData) {
        favouriteThisOutputData fTOutputData;
        boolean isFavourite = fTInputData.getIsFavourite();
        Recipe recipe = new Recipe(fTInputData.getTitle(), fTInputData.getReadyInMinutes(),
                fTInputData.getServings(),fTInputData.getSummary(), fTInputData.getIngredients(),
                fTInputData.getInstructions(), fTInputData.getImage(), fTInputData.getId());

        if (isFavourite) {
            favouriteRecipes.removeOneRecipe(recipe);
            fTOutputData = new favouriteThisOutputData(false);

        } else {
            favouriteRecipes.setOneFavouriteRecipe(recipe);
            fTOutputData = new favouriteThisOutputData(true);
        }

        fTPresenter.prepareSuccessView(fTOutputData);
    }


}
