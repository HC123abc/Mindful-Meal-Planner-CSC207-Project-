package use_case.favouriteThis;

import data_access.InMemoryDataAccess.InMemoryDataAccessUserInterface;
import entity.*;
import use_case.reRoll.ReRollOutputBoundary;

public class favouriteThisInteractor implements favouriteThisInputBoundary{
    private User user;
    private FavouriteRecipes favouriteRecipes;  // a FavouriteRecipes object
    private favouriteThisOutputBoundary fTPresenter;
    private InMemoryDataAccessUserInterface inMemoryDataAccessUser;

    public favouriteThisInteractor(InMemoryDataAccessUserInterface inMemoryDataAccessUser,
                                   favouriteThisOutputBoundary fTPresenter) {
        this.inMemoryDataAccessUser = inMemoryDataAccessUser;
        this.fTPresenter = fTPresenter;
    }


    @Override
    public void execute(favouriteThisInputData fTInputData) {
        this.user = inMemoryDataAccessUser.getActiveUser();
        this.favouriteRecipes = user.getFavouriteRecipes();
        boolean isFavourite = fTInputData.getIsFavourite();
        Recipe recipe = new Recipe(fTInputData.getTitle(),fTInputData.getReadyInMinutes(),fTInputData.getServings(),fTInputData.getSummary(),fTInputData.getExtendedIngredients(),fTInputData.getExtendedInstructions(),fTInputData.getRecipeImageURL(),fTInputData.getId());

        if (isFavourite) {
            favouriteRecipes.removeOneRecipe(recipe);
            fTPresenter.prepareSuccessView(new favouriteThisOutputData(false));

        } else {
            favouriteRecipes.setOneFavouriteRecipe(recipe);
            fTPresenter.prepareSuccessView(new favouriteThisOutputData(true));
        }


    }


}
