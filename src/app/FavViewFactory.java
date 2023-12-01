package app;

import data_access.FavRecipeCardApi.FavRecipeCardApi;
import data_access.InMemoryDataAccess.InMemoryDataAccessUserInterface;
import interface_adapter.CookThis.CookThisController;
import interface_adapter.FavView.FavViewController;
import interface_adapter.FavView.FavViewPresenter;
import interface_adapter.FavView.FavViewViewModel;
import interface_adapter.Finish.FinishController;
import interface_adapter.Finish.FinishPresenter;
import interface_adapter.Finish.FinishViewModel;
import interface_adapter.ViewManagerModel;
import interface_adapter.unfavouriteThis.UnfavouriteThisController;
import interface_adapter.unfavouriteThis.UnfavouriteThisPresenter;
import use_case.FavView.FavViewDataAccessInterface;
import use_case.FavView.FavViewInputBoundary;
import use_case.FavView.FavViewInteractor;
import use_case.Finish.FinishInteractor;
import use_case.unfavouriteThis.UnfavouriteThisInputBoundary;
import use_case.unfavouriteThis.UnfavouriteThisInteractor;
import view.FavView;

public class FavViewFactory {
    public static FavView createView (ViewManagerModel viewManagerModel, FavViewViewModel favViewViewModel, CookThisController cookThisController, InMemoryDataAccessUserInterface inMemoryDataAccessUser) {
        FinishViewModel finishViewModel3 = new FinishViewModel();
        FinishPresenter finishPresenter3 = new FinishPresenter(viewManagerModel,finishViewModel3);
        FinishInteractor finishInteractor3 = new FinishInteractor(finishPresenter3);
        FinishController finishController3 = new FinishController(finishInteractor3);
        FavViewDataAccessInterface favViewDataAccessInterface = new FavRecipeCardApi();
        UnfavouriteThisPresenter unfavouriteThisPresenter = new UnfavouriteThisPresenter(favViewViewModel,viewManagerModel);
        UnfavouriteThisInputBoundary unfavouriteThisInteractor = new UnfavouriteThisInteractor(favViewDataAccessInterface, inMemoryDataAccessUser, unfavouriteThisPresenter);
        UnfavouriteThisController unfavouriteThisController = new UnfavouriteThisController(unfavouriteThisInteractor);
        FavView favView = new FavView(favViewViewModel,finishController3,cookThisController, unfavouriteThisController);
        return favView;
    }
}
