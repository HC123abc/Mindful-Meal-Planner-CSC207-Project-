package interface_adapter.FavView;

import interface_adapter.CookThisOrReRoll.CookThisOrReRollState;
import interface_adapter.CookThisOrReRoll.CookThisOrReRollViewModel;
import interface_adapter.ViewManagerModel;
import use_case.FavView.FavViewOutputBoundary;
import use_case.FavView.FavViewOutputData;


public class FavViewPresenter implements FavViewOutputBoundary {
    private final FavViewViewModel favViewViewModel;
    private ViewManagerModel viewManagerModel;

    public FavViewPresenter(FavViewViewModel favViewViewModel, ViewManagerModel viewManagerModel) {
        this.favViewViewModel = favViewViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void prepareSuccessView(FavViewOutputData recipeOutputData) {
        FavViewState favViewState = favViewViewModel.getState();
        favViewState.setRecipeWithCardImage(recipeOutputData.getRecipeWithCardImage());
        this.favViewViewModel.setState(favViewState);
        favViewViewModel.firePropertyChanged();
        viewManagerModel.setActiveView(favViewViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {
//      main view model
        FavViewState favViewState = favViewViewModel.getState();
        favViewState.setRecipeError(error);
        favViewViewModel.firePropertyChanged();
    }
}
