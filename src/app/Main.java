package app;

import data_access.GenerateRecipe.GenerateRecipeApi;
import entity.Preference;
import entity.RandomRecipe;
import entity.RecipeFactory;
import interface_adapter.CookThis.CookThisController;
import interface_adapter.CookThis.CookThisPresenter;
import interface_adapter.CookThis.CookThisViewModel;
import interface_adapter.CookThisOrReRoll.CookThisOrReRollPresenter;
import interface_adapter.CookThisOrReRoll.CookThisOrReRollViewModel;
import interface_adapter.CookThisOrReRoll.GenerateRecipeController;
import interface_adapter.ReRoll.ReRollController;
import interface_adapter.ReRoll.ReRollPresenter;
import interface_adapter.ViewManagerModel;

import use_case.cookThis.CookThisInteractor;
import use_case.generateRecipe.GenerateRecipeDataAccessInterface;
import use_case.generateRecipe.GenerateRecipeInteractor;
import use_case.generateRecipe.GenerateRecipeOutputBoundary;
import use_case.reRoll.ReRollInputBoundary;
import use_case.reRoll.ReRollInteractor;
import view.CookThisOrReRollView;
import view.CookThisView;
import view.MainPageView;
import view.ViewManager;

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
        GenerateRecipeInteractor generateRecipeInteractor = new GenerateRecipeInteractor(generateRecipeAPI,generateRecipeOutputBoundary, preference, randomRecipe, recipeFactory);
        ReRollPresenter reRollPresenter = new ReRollPresenter(cookThisOrReRollViewModel,viewManagerModel);
        ReRollInputBoundary reRollInputBoundary = new ReRollInteractor(randomRecipe,reRollPresenter,recipeFactory);
        ReRollController reRollController = new ReRollController(reRollInputBoundary);
        CookThisViewModel cookThisViewModel = new CookThisViewModel();
        CookThisPresenter cookThisPresenter = new CookThisPresenter(cookThisViewModel,viewManagerModel);
        CookThisInteractor cookThisInteractor = new CookThisInteractor(cookThisPresenter);
        CookThisController cookThisController = new CookThisController(cookThisInteractor);

        CookThisOrReRollView cookThisOrReRollView = new CookThisOrReRollView(cookThisOrReRollViewModel,reRollController,  cookThisController, viewManagerModel);
        views.add(cookThisOrReRollView, cookThisOrReRollView.viewName);

        CookThisView cookThisView = new CookThisView(cookThisViewModel, viewManagerModel);
        views.add(cookThisView, cookThisView.viewName);

        GenerateRecipeController generateRecipeController = new GenerateRecipeController(generateRecipeInteractor);
        MainPageView mainPageView = new MainPageView(generateRecipeController,cookThisOrReRollViewModel);
        views.add(mainPageView, mainPageView.viewName);

        viewManagerModel.setActiveView(mainPageView.viewName);
        viewManagerModel.firePropertyChanged();

        application.pack();
        application.setVisible(true);
    }
}