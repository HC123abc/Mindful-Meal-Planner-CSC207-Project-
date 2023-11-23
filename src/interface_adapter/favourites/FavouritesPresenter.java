package interface_adapter.favourites;

import interface_adapter.ViewManagerModel;
import use_case.favourites.FavouritesOutputData;

public class FavouritesPresenter {
    private final FavouritesViewModel favouritesViewModel;
    private ViewManagerModel viewManagerModel;

    public FavouritesPresenter(FavouritesViewModel favouritesViewModel,
                               ViewManagerModel viewManagerModel) {
        this.favouritesViewModel = favouritesViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    // switch to favouritesView
    public void prepareSuccessView(FavouritesOutputData favouritesOutputData) {
        FavouritesState favouritesState = favouritesViewModel.getState();

        //TODO UPDATE THE STATE USING SET___ AND THE OUTPUTDATA PROPERTIES

        this.favouritesViewModel.setState(favouritesState);

        favouritesViewModel.firePropertyChanged();

        viewManagerModel.setActiveView(favouritesViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
