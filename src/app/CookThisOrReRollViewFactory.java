package app;

import data_access.GenerateRecipe.GenerateRecipeApi;
import data_access.InMemoryDataAccess.InMemoryDataAccessUserInterface;
import entity.RecipeFactory;
import entity.SimpleRecipeFactoryInterface;
import interface_adapter.CookThis.CookThisController;
import interface_adapter.CookThis.CookThisPresenter;
import interface_adapter.CookThis.CookThisViewModel;
import interface_adapter.CookThisOrReRoll.CookThisOrReRollPresenter;
import interface_adapter.CookThisOrReRoll.CookThisOrReRollViewModel;
import interface_adapter.FavouriteThis.favouriteThisController;
import interface_adapter.FavouriteThis.favouriteThisPresenter;
import interface_adapter.Finish.FinishController;
import interface_adapter.Finish.FinishPresenter;
import interface_adapter.Finish.FinishViewModel;
import interface_adapter.ReRoll.ReRollController;
import interface_adapter.ReRoll.ReRollPresenter;
import interface_adapter.ViewManagerModel;
import use_case.Finish.FinishInteractor;
import use_case.cookThis.CookThisInteractor;
import use_case.favouriteThis.favouriteThisInteractor;
import use_case.generateRecipe.GenerateRecipeDataAccessInterface;
import use_case.generateRecipe.GenerateRecipeInteractor;
import use_case.generateRecipe.GenerateRecipeOutputBoundary;
import use_case.reRoll.ReRollInputBoundary;
import use_case.reRoll.ReRollInteractor;
import view.CookThisOrReRollView;


public class CookThisOrReRollViewFactory {
    public static CookThisOrReRollView createView(ViewManagerModel viewManagerModel, InMemoryDataAccessUserInterface inMemoryDataAccessUser, SimpleRecipeFactoryInterface recipeFactory, CookThisController cookThisController, CookThisOrReRollViewModel cookThisOrReRollViewModel,GenerateRecipeOutputBoundary generateRecipeOutputBoundary ) {
        GenerateRecipeDataAccessInterface generateRecipeAPI = new GenerateRecipeApi();
        // Create your GenerateRecipeOutputBoundary implementation
        GenerateRecipeInteractor generateRecipeInteractor = new GenerateRecipeInteractor(generateRecipeAPI,generateRecipeOutputBoundary, inMemoryDataAccessUser, recipeFactory);

        ReRollPresenter reRollPresenter = new ReRollPresenter(cookThisOrReRollViewModel,viewManagerModel);
        ReRollInputBoundary reRollInputBoundary = new ReRollInteractor(inMemoryDataAccessUser,reRollPresenter,recipeFactory);
        ReRollController reRollController = new ReRollController(reRollInputBoundary);



        favouriteThisPresenter favouriteThisPresenter = new favouriteThisPresenter(cookThisOrReRollViewModel,viewManagerModel);
        favouriteThisInteractor favouriteThisInteractor = new favouriteThisInteractor(inMemoryDataAccessUser,favouriteThisPresenter);
        favouriteThisController favouriteThisController = new favouriteThisController(favouriteThisInteractor);
        // Ryan's changed signature, to be fixed. CookThisOrReRollView cookThisOrReRollView = new CookThisOrReRollView(cookThisOrReRollViewModel,reRollController, cookThisController, favouriteThisController, viewManagerModel);

        FinishViewModel finishViewModel1 = new FinishViewModel();
        FinishPresenter finishPresenter1 = new FinishPresenter(viewManagerModel,finishViewModel1);
        FinishInteractor finishInteractor1 = new FinishInteractor(finishPresenter1);
        FinishController finishController1 = new FinishController(finishInteractor1);

        CookThisOrReRollView cookThisOrReRollView = new CookThisOrReRollView(cookThisOrReRollViewModel,reRollController,  cookThisController, favouriteThisController, finishController1, viewManagerModel);
        return cookThisOrReRollView;
    }
}
