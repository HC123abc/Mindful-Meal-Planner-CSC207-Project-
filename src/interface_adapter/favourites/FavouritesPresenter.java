package interface_adapter.favourites;

import interface_adapter.ViewManagerModel;
import interface_adapter.favourites.FavouritesState;

import use_case.favourites.FavouritesOutputBoundary;
import use_case.favourites.FavouritesOutputData;

import java.util.Map;

public class FavouritesPresenter implements FavouritesOutputBoundary {
    private final FavouritesViewModel favouritesViewModel;
    private ViewManagerModel viewManagerModel;

    public FavouritesPresenter(FavouritesViewModel favouritesViewModel,
                               ViewManagerModel viewManagerModel) {
        this.favouritesViewModel = favouritesViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    // Will switch to favouritesView
    public void prepareSuccessView(FavouritesOutputData favouritesOutputData) {
        FavouritesState favouritesState = favouritesViewModel.getState();

        Map<Map<String, String>, String> favouriteRecipes = favouritesOutputData.getAllRecipes();
        favouritesState.setFavouriteRecipes(favouriteRecipes);

        this.favouritesViewModel.setState(favouritesState);

        favouritesViewModel.firePropertyChanged();

        viewManagerModel.setActiveView(favouritesViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
