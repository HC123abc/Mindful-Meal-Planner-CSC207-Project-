package interface_adapter.unfavouriteThis;

import interface_adapter.FavView.FavViewState;
import interface_adapter.FavView.FavViewViewModel;
import interface_adapter.ViewManagerModel;

import use_case.unfavouriteThis.UnfavouriteThisOutputBoundary;
import use_case.unfavouriteThis.UnfavouriteThisOutputData;

import java.util.Map;

public class UnfavouriteThisPresenter implements UnfavouriteThisOutputBoundary {
    private final FavViewViewModel favViewViewModel;
    private ViewManagerModel viewManagerModel;
    public UnfavouriteThisPresenter(FavViewViewModel favViewViewModel, ViewManagerModel viewManagerModel) {
        this.favViewViewModel = favViewViewModel;
        this.viewManagerModel = viewManagerModel;
    }
    @Override
    public void prepareSuccessView(UnfavouriteThisOutputData unfavouriteThisOutputData) {
        FavViewState favViewState = favViewViewModel.getState();

        Map<String, Map<String, String>> recipes = unfavouriteThisOutputData.getRecipeOutput();
        favViewState.setRecipeWithCardImage(recipes);

        this.favViewViewModel.setState(favViewState);

        favViewViewModel.firePropertyChanged();
        viewManagerModel.setActiveView(favViewViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

//    @Override
//    public void prepareFailView(String error) {
//        FavViewState favViewState = favViewViewModel.getState();
//        favViewState.setRecipeError(error);
//        favViewViewModel.firePropertyChanged();
//
//        viewManagerModel.setActiveView();
//    }
}
