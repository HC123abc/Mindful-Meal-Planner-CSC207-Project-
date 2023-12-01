package app;

import data_access.FavRecipeCardApi.FavRecipeCardApi;
import data_access.GenerateRecipe.GenerateRecipeApi;
import data_access.InMemoryDataAccess.InMemoryDataAccessUser;
import data_access.InMemoryDataAccess.InMemoryDataAccessUserInterface;
import data_access.User.UserDataAccessObject;
import entity.*;
import interface_adapter.CookThis.CookThisController;
import interface_adapter.CookThis.CookThisPresenter;
import interface_adapter.CookThis.CookThisViewModel;
import interface_adapter.CookThisOrReRoll.CookThisOrReRollPresenter;
import interface_adapter.CookThisOrReRoll.CookThisOrReRollViewModel;
import interface_adapter.CookThisOrReRoll.GenerateRecipeController;
import interface_adapter.FavView.FavViewController;
import interface_adapter.FavView.FavViewPresenter;
import interface_adapter.FavView.FavViewViewModel;
import interface_adapter.FavouriteThis.favouriteThisController;
import interface_adapter.Finish.FinishController;
import interface_adapter.Finish.FinishPresenter;
import interface_adapter.Finish.FinishViewModel;
import interface_adapter.Login.LoginController;
import interface_adapter.Login.LoginPresenter;
import interface_adapter.Login.LoginViewModel;
import interface_adapter.Preference.PreferenceController;
import interface_adapter.Preference.PreferencePresenter;
import interface_adapter.Preference.PreferenceViewModel;
import interface_adapter.ReRoll.ReRollController;
import interface_adapter.ReRoll.ReRollPresenter;
import interface_adapter.RedirectToPreference.RedirectToPreferenceController;
import interface_adapter.RedirectToPreference.RedirectToPreferencePresenter;
import interface_adapter.RedirectToPreference.RedirectToPreferenceViewModel;
import interface_adapter.ReturnToPreviousView.ReturnToPreviousViewController;
import interface_adapter.ReturnToPreviousView.ReturnToPreviousViewPresenter;
import interface_adapter.ReturnToPreviousView.ReturnToPreviousViewViewModel;
import interface_adapter.unfavouriteThis.UnfavouriteThisController;
import interface_adapter.unfavouriteThis.UnfavouriteThisPresenter;
import interface_adapter.ViewManagerModel;
import interface_adapter.FavouriteThis.*;

import interface_adapter.signUp.SignUpController;
import interface_adapter.signUp.SignUpPresenter;
import interface_adapter.signUp.SignUpViewModel;
import use_case.FavView.FavViewDataAccessInterface;
import use_case.FavView.FavViewInputBoundary;
import use_case.FavView.FavViewInteractor;
import use_case.Finish.FinishInteractor;
import use_case.Preference.PreferenceInteractor;
import use_case.RedirectToPreference.RedirectToPreferenceInteractor;
import use_case.ReturnToPreviousView.ReturnToPreviousViewInteractor;
import use_case.unfavouriteThis.UnfavouriteThisOutputData;
import use_case.unfavouriteThis.UnfavouriteThisInteractor;
import use_case.unfavouriteThis.UnfavouriteThisOutputBoundary;
import use_case.unfavouriteThis.UnfavouriteThisInputData;
import use_case.unfavouriteThis.UnfavouriteThisInputBoundary;
import use_case.cookThis.CookThisInteractor;
import use_case.favouriteThis.favouriteThisInteractor;
import use_case.generateRecipe.GenerateRecipeDataAccessInterface;
import use_case.generateRecipe.GenerateRecipeInteractor;
import use_case.generateRecipe.GenerateRecipeOutputBoundary;
import use_case.login.LoginInteractor;
import use_case.reRoll.ReRollInputBoundary;
import use_case.reRoll.ReRollInteractor;
import use_case.signUp.SignUpInteractor;
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
//      These are need in multiple views that is why they are here and not in a factory.
        CookThisViewModel cookThisViewModel = new CookThisViewModel();
        CookThisPresenter cookThisPresenter = new CookThisPresenter(cookThisViewModel,viewManagerModel);
        CookThisInteractor cookThisInteractor = new CookThisInteractor(cookThisPresenter);
        CookThisController cookThisController = new CookThisController(cookThisInteractor);

        SimpleRecipeFactoryInterface recipeFactory = new RecipeFactory();
        GenerateRecipeDataAccessInterface generateRecipeAPI = new GenerateRecipeApi();
        CookThisOrReRollViewModel cookThisOrReRollViewModel= new CookThisOrReRollViewModel();
        // Create your GenerateRecipeOutputBoundary implementation
        GenerateRecipeOutputBoundary generateRecipeOutputBoundary = new CookThisOrReRollPresenter(cookThisOrReRollViewModel,viewManagerModel);
        GenerateRecipeInteractor generateRecipeInteractor = new GenerateRecipeInteractor(generateRecipeAPI,generateRecipeOutputBoundary, inMemoryDataAccessUser, recipeFactory);
        // The data for the views, such as username and password, are in the ViewModels.
        // This information will be changed by a presenter object that is reporting the
        // results from the use case. The ViewModels are observable, and will
        // be observed by the Views.
//      These are need in multiple views that is why they are here and not in a factory.
        CookThisOrReRollViewFactory cookThisOrReRollViewFactory= new CookThisOrReRollViewFactory();
        CookThisOrReRollView cookThisOrReRollView = cookThisOrReRollViewFactory.createView( viewManagerModel, inMemoryDataAccessUser, recipeFactory, cookThisController, cookThisOrReRollViewModel, generateRecipeOutputBoundary);
        views.add(cookThisOrReRollView, cookThisOrReRollView.viewName);

        CookThisView cookThisView = CookThisViewFactory.createView(viewManagerModel,cookThisViewModel);
        views.add(cookThisView, cookThisView.viewName);
//      These are need in multiple views that is why they are here and not in a factory.
        RedirectToPreferenceViewModel redirectToPreferenceViewModel = new RedirectToPreferenceViewModel();
        RedirectToPreferencePresenter redirectToPreferencePresenter = new RedirectToPreferencePresenter(redirectToPreferenceViewModel,viewManagerModel);
        RedirectToPreferenceInteractor redirectToPreferenceInteractor = new RedirectToPreferenceInteractor(inMemoryDataAccessUser,redirectToPreferencePresenter);
        RedirectToPreferenceController redirectToPreferenceController = new RedirectToPreferenceController(redirectToPreferenceInteractor);


        PreferenceView preferenceView = PreferenceViewFactory.createView(viewManagerModel,inMemoryDataAccessUser,redirectToPreferenceViewModel);
        views.add(preferenceView, preferenceView.viewName);

//      These are need in multiple views that is why they are here and not in a factory.
        FavViewViewModel favViewViewModel = new FavViewViewModel();
        FavViewDataAccessInterface favViewDataAccessInterface = new FavRecipeCardApi();
        FavViewPresenter favViewPresenter = new FavViewPresenter(favViewViewModel,viewManagerModel);
        FavViewInputBoundary favViewInteractor = new FavViewInteractor(favViewDataAccessInterface, favViewPresenter, inMemoryDataAccessUser );
        FavViewController favViewController = new FavViewController(favViewInteractor);
        FavView favView = FavViewFactory.createView(viewManagerModel,favViewViewModel,cookThisController, inMemoryDataAccessUser);
        views.add(favView, favView.viewName);

        GenerateRecipeController generateRecipeController = new GenerateRecipeController(generateRecipeInteractor);
        MainPageView mainPageView = new MainPageView(generateRecipeController,cookThisOrReRollViewModel,redirectToPreferenceController,favViewController,favViewViewModel);
        views.add(mainPageView, mainPageView.viewName);

        LoginViewModel LVM = new LoginViewModel();
        UserDataAccessObject UDAO = new UserDataAccessObject();
        LoginPresenter LoginPresenter = new LoginPresenter(LVM, viewManagerModel, mainPageView.viewName);
        LoginInteractor loginInteractor = new LoginInteractor(UDAO, LoginPresenter, inMemoryDataAccessUser);
        LoginController LoginController = new LoginController(loginInteractor);
        System.out.println(inMemoryDataAccessUser.getActiveUser().getUsername());
        SignUpViewModel SVM = new SignUpViewModel();
        SignUpPresenter signUpPresenter = new SignUpPresenter(SVM, mainPageView.viewName, viewManagerModel);
        SignUpInteractor signUpInteractor = new SignUpInteractor(UDAO, signUpPresenter, inMemoryDataAccessUser);
        SignUpController signUpController = new SignUpController(signUpInteractor);
        signUpView SignUpView = new signUpView(signUpController, SVM, LoginController);
        LoginView loginView = new LoginView(LVM, LoginController, signUpController);
        views.add(loginView, LVM.getViewName());
        views.add(SignUpView, SVM.getViewName());

        System.out.println(loginView.viewName);
        viewManagerModel.setActiveView(loginView.viewName);
//        viewManagerModel.setActiveView(mainPageView.viewName);
        viewManagerModel.firePropertyChanged();

        application.pack();
        application.setVisible(true);
    }
}