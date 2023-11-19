package app;

import data_access.GenerateRecipe.GenerateRecipeApi;
import data_access.InMemoryDataAccess.InMemoryDataAccessUser;
import data_access.InMemoryDataAccess.InMemoryDataAccessUserInterface;
import entity.Preference;
import entity.RandomRecipe;
import entity.RecipeFactory;
import entity.User;
import interface_adapter.CookThis.CookThisController;
import interface_adapter.CookThis.CookThisPresenter;
import interface_adapter.CookThis.CookThisViewModel;
import interface_adapter.CookThisOrReRoll.CookThisOrReRollPresenter;
import interface_adapter.CookThisOrReRoll.CookThisOrReRollViewModel;
import interface_adapter.CookThisOrReRoll.GenerateRecipeController;
import interface_adapter.Finish.FinishController;
import interface_adapter.Finish.FinishPresenter;
import interface_adapter.Finish.FinishViewModel;
import interface_adapter.Preference.PreferenceController;
import interface_adapter.Preference.PreferencePresenter;
import interface_adapter.Preference.PreferenceViewModel;
import interface_adapter.ReRoll.ReRollController;
import interface_adapter.ReRoll.ReRollPresenter;
import interface_adapter.RedirectToPreference.RedirectToPreferenceController;
import interface_adapter.RedirectToPreference.RedirectToPreferencePresenter;
import interface_adapter.RedirectToPreference.RedirectToPreferenceViewModel;
import interface_adapter.ViewManagerModel;

import use_case.Finish.FinishInteractor;
import use_case.Preference.PreferenceInteractor;
import use_case.RedirectToPreference.RedirectToPreferenceInteractor;
import use_case.cookThis.CookThisInteractor;
import use_case.generateRecipe.GenerateRecipeDataAccessInterface;
import use_case.generateRecipe.GenerateRecipeInteractor;
import use_case.generateRecipe.GenerateRecipeOutputBoundary;
import use_case.reRoll.ReRollInputBoundary;
import use_case.reRoll.ReRollInteractor;
import view.*;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        // Build the main program window, the main panel containing the
        // various cards, and the layout, and stitch them together.

        // The main application window.
        JFrame application = new JFrame("Main");
        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        CardLayout cardLayout = new CardLayout();

        // The various View objects. Only one view is visible at a time.
        JPanel views = new JPanel(cardLayout);
        application.add(views);

        // This keeps track of and manages which view is currently showing.
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        new ViewManager(views, cardLayout, viewManagerModel);

        InMemoryDataAccessUserInterface inMemoryDataAccessUser = new InMemoryDataAccessUser();
        User user= new User("test","test");
        inMemoryDataAccessUser.setActiveUser(user);

        // The data for the views, such as username and password, are in the ViewModels.
        // This information will be changed by a presenter object that is reporting the
        // results from the use case. The ViewModels are observable, and will
        // be observed by the Views.
        Preference preference = new Preference();
        GenerateRecipeDataAccessInterface generateRecipeAPI = new GenerateRecipeApi();
        RandomRecipe randomRecipe = new RandomRecipe();
        // Create your GenerateRecipeOutputBoundary implementation
        CookThisOrReRollViewModel cookThisOrReRollViewModel= new CookThisOrReRollViewModel();
        GenerateRecipeOutputBoundary generateRecipeOutputBoundary = new CookThisOrReRollPresenter(cookThisOrReRollViewModel,viewManagerModel);
        // Create an instance of GenerateRecipe
        RecipeFactory recipeFactory = new RecipeFactory();
        GenerateRecipeInteractor generateRecipeInteractor = new GenerateRecipeInteractor(generateRecipeAPI,generateRecipeOutputBoundary, inMemoryDataAccessUser, recipeFactory);
        ReRollPresenter reRollPresenter = new ReRollPresenter(cookThisOrReRollViewModel,viewManagerModel);
        ReRollInputBoundary reRollInputBoundary = new ReRollInteractor(inMemoryDataAccessUser,reRollPresenter,recipeFactory);
        ReRollController reRollController = new ReRollController(reRollInputBoundary);
        CookThisViewModel cookThisViewModel = new CookThisViewModel();
        CookThisPresenter cookThisPresenter = new CookThisPresenter(cookThisViewModel,viewManagerModel);
        CookThisInteractor cookThisInteractor = new CookThisInteractor(cookThisPresenter);
        CookThisController cookThisController = new CookThisController(cookThisInteractor);

        FinishViewModel finishViewModel1 = new FinishViewModel();
        FinishPresenter finishPresenter1 = new FinishPresenter(viewManagerModel,finishViewModel1);
        FinishInteractor finishInteractor1 = new FinishInteractor(finishPresenter1);
        FinishController finishController1 = new FinishController(finishInteractor1);
        CookThisOrReRollView cookThisOrReRollView = new CookThisOrReRollView(cookThisOrReRollViewModel,reRollController,  cookThisController, finishController1, viewManagerModel);
        views.add(cookThisOrReRollView, cookThisOrReRollView.viewName);

        FinishViewModel finishViewModel = new FinishViewModel();
        FinishPresenter finishPresenter = new FinishPresenter(viewManagerModel,finishViewModel );
        FinishInteractor finishInteractor = new FinishInteractor(finishPresenter);
        FinishController finishController = new FinishController(finishInteractor);
        CookThisView cookThisView = new CookThisView(cookThisViewModel, finishController);
        views.add(cookThisView, cookThisView.viewName);

        RedirectToPreferenceViewModel redirectToPreferenceViewModel = new RedirectToPreferenceViewModel();
        RedirectToPreferencePresenter redirectToPreferencePresenter = new RedirectToPreferencePresenter(redirectToPreferenceViewModel,viewManagerModel);
        RedirectToPreferenceInteractor redirectToPreferenceInteractor = new RedirectToPreferenceInteractor(inMemoryDataAccessUser,redirectToPreferencePresenter);
        RedirectToPreferenceController redirectToPreferenceController = new RedirectToPreferenceController(redirectToPreferenceInteractor);

        FinishViewModel finishViewModel2 = new FinishViewModel();
        FinishPresenter finishPresenter2 = new FinishPresenter(viewManagerModel,finishViewModel2);
        FinishInteractor finishInteractor2 = new FinishInteractor(finishPresenter2);
        FinishController finishController2 = new FinishController(finishInteractor2);
        PreferenceViewModel preferenceViewModel = new PreferenceViewModel();
        PreferencePresenter preferencePresenter = new PreferencePresenter(preferenceViewModel,viewManagerModel);
        PreferenceInteractor preferenceInteractor = new PreferenceInteractor(inMemoryDataAccessUser,preferencePresenter);
        PreferenceController preferenceController = new PreferenceController(preferenceInteractor);
        PreferenceView preferenceView = new PreferenceView(preferenceViewModel,preferenceController ,finishController2);
        views.add(preferenceView, preferenceView.viewName);

        GenerateRecipeController generateRecipeController = new GenerateRecipeController(generateRecipeInteractor);
        MainPageView mainPageView = new MainPageView(generateRecipeController,cookThisOrReRollViewModel,redirectToPreferenceController);
        views.add(mainPageView, mainPageView.viewName);

        viewManagerModel.setActiveView(mainPageView.viewName);
        viewManagerModel.firePropertyChanged();

        application.pack();
        application.setVisible(true);
    }
}