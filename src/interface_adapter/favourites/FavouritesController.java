package interface_adapter.favourites;

import use_case.favourites.FavouritesInputBoundary;

public class FavouritesController {
    final FavouritesInputBoundary favouritesInteractor;

    public FavouritesController(FavouritesInputBoundary favouritesInteractor) {
        this.favouritesInteractor = favouritesInteractor;
    }

    public void execute() { favouritesInteractor.execute(); }
}
