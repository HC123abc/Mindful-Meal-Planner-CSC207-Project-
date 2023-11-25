package use_case.favourites;

import data_access.InMemoryDataAccess.InMemoryDataAccessUser;
import data_access.InMemoryDataAccess.InMemoryDataAccessUserInterface;

import entity.RecipeFactory;
import entity.User;
import entity.FavouriteRecipes;

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
        this.favouritesPresenter = favouritesPresenter;
    }

    @Override
    public void execute() {
        //TODO ADD IMPLEMENTATION, GETTING AND FORMATTING THE FAV RECIPES



        FavouritesOutputData favouritesOutputData = new FavouritesOutputData(); //TODO ADD ARGS

        this.favouritesPresenter.prepareSuccessView(favouritesOutputData);
    }
}
