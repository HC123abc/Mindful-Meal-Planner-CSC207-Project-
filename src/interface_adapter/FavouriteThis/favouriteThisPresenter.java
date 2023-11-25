package interface_adapter.FavouriteThis;

import interface_adapter.CookThisOrReRoll.CookThisOrReRollState;
import interface_adapter.CookThisOrReRoll.CookThisOrReRollViewModel;
import interface_adapter.ViewManagerModel;

import use_case.favouriteThis.favouriteThisOutputBoundary;
import use_case.favouriteThis.favouriteThisOutputData;

public class favouriteThisPresenter implements favouriteThisOutputBoundary{
    private final CookThisOrReRollViewModel cTORViewModel;
    private ViewManagerModel viewManagerModel;

    public favouriteThisPresenter(CookThisOrReRollViewModel cTORViewModel, ViewManagerModel viewManagerModel) {
        this.cTORViewModel = cTORViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void prepareSuccessView(favouriteThisOutputData fTOutputData) {
        // gets the state, changes the isFavourited attribute, and updates the state
        CookThisOrReRollState cTORState = cTORViewModel.getState();
        cTORState.setIsFavourite(fTOutputData.getIsFavourite());
        cTORState.setFavButtonClicked(true);
        this.cTORViewModel.setState(cTORState);
        cTORViewModel.firePropertyChanged();
        System.out.println(fTOutputData.getIsFavourite());
    }
}
