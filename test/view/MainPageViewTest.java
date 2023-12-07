package view;

import data_access.FavRecipeCardApi.FavRecipeCardApi;
import data_access.GenerateRecipe.GenerateRecipeApi;
import data_access.InMemoryDataAccess.InMemoryDataAccessUser;
import data_access.InMemoryDataAccess.InMemoryDataAccessUserInterface;
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
import interface_adapter.FavouriteThis.favouriteThisPresenter;
import interface_adapter.Finish.FinishController;
import interface_adapter.Finish.FinishPresenter;
import interface_adapter.Finish.FinishViewModel;
import interface_adapter.Logout.LogoutController;
import interface_adapter.Logout.LogoutPresenter;
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
import interface_adapter.ViewManagerModel;
import interface_adapter.unfavouriteThis.UnfavouriteThisController;
import interface_adapter.unfavouriteThis.UnfavouriteThisPresenter;
import org.junit.jupiter.api.Test;
import use_case.FavView.FavViewDataAccessInterface;
import use_case.FavView.FavViewInputBoundary;
import use_case.FavView.FavViewInteractor;
import use_case.Finish.FinishInteractor;
import use_case.Preference.PreferenceInteractor;
import use_case.RedirectToPreference.RedirectToPreferenceInteractor;
import use_case.ReturnToPreviousView.ReturnToPreviousViewInteractor;
import use_case.cookThis.CookThisInteractor;
import use_case.favouriteThis.favouriteThisInteractor;
import use_case.generateRecipe.GenerateRecipeDataAccessInterface;
import use_case.generateRecipe.GenerateRecipeInteractor;
import use_case.generateRecipe.GenerateRecipeOutputBoundary;
import use_case.logout.LogoutInteractor;
import use_case.reRoll.ReRollInputBoundary;
import use_case.reRoll.ReRollInteractor;
import use_case.unfavouriteThis.UnfavouriteThisInputBoundary;
import use_case.unfavouriteThis.UnfavouriteThisInteractor;

import javax.swing.*;

import java.awt.*;

//button tests work for windows, may not work for mac os: sample size 1
public class MainPageViewTest {

    @Test
    void testButtonActions() {
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
        SimpleRecipeFactoryInterface recipeFactory = new RecipeFactory();

        GenerateRecipeInteractor generateRecipeInteractor = new GenerateRecipeInteractor(generateRecipeAPI,generateRecipeOutputBoundary, inMemoryDataAccessUser, recipeFactory);

        ReRollPresenter reRollPresenter = new ReRollPresenter(cookThisOrReRollViewModel,viewManagerModel);
        ReRollInputBoundary reRollInputBoundary = new ReRollInteractor(inMemoryDataAccessUser,reRollPresenter,recipeFactory);
        ReRollController reRollController = new ReRollController(reRollInputBoundary);

        CookThisViewModel cookThisViewModel = new CookThisViewModel();
        CookThisPresenter cookThisPresenter = new CookThisPresenter(cookThisViewModel,viewManagerModel);
        CookThisInteractor cookThisInteractor = new CookThisInteractor(cookThisPresenter);
        CookThisController cookThisController = new CookThisController(cookThisInteractor);

        favouriteThisPresenter favouriteThisPresenter = new favouriteThisPresenter(cookThisOrReRollViewModel,viewManagerModel);
        favouriteThisInteractor favouriteThisInteractor = new favouriteThisInteractor(inMemoryDataAccessUser,favouriteThisPresenter);
        favouriteThisController favouriteThisController = new favouriteThisController(favouriteThisInteractor);
        // Ryan's changed signature, to be fixed. CookThisOrReRollView cookThisOrReRollView = new CookThisOrReRollView(cookThisOrReRollViewModel,reRollController, cookThisController, favouriteThisController, viewManagerModel);

        FinishViewModel finishViewModel1 = new FinishViewModel();
        FinishPresenter finishPresenter1 = new FinishPresenter(viewManagerModel,finishViewModel1);
        FinishInteractor finishInteractor1 = new FinishInteractor(finishPresenter1);
        FinishController finishController1 = new FinishController(finishInteractor1);

        CookThisOrReRollView cookThisOrReRollView = new CookThisOrReRollView(cookThisOrReRollViewModel,reRollController,  cookThisController, favouriteThisController, finishController1, viewManagerModel);
        views.add(cookThisOrReRollView, cookThisOrReRollView.viewName);

        FinishViewModel finishViewModel = new FinishViewModel();
        FinishPresenter finishPresenter = new FinishPresenter(viewManagerModel,finishViewModel);
        FinishInteractor finishInteractor = new FinishInteractor(finishPresenter);
        FinishController finishController = new FinishController(finishInteractor);
        ReturnToPreviousViewViewModel returnToPreviousViewViewModel = new ReturnToPreviousViewViewModel();
        ReturnToPreviousViewPresenter returnToPreviousViewPresenter = new ReturnToPreviousViewPresenter(viewManagerModel,returnToPreviousViewViewModel);
        ReturnToPreviousViewInteractor returnToPreviousViewInteractor = new ReturnToPreviousViewInteractor(returnToPreviousViewPresenter);
        ReturnToPreviousViewController returnToPreviousViewController = new ReturnToPreviousViewController(returnToPreviousViewInteractor);
        CookThisView cookThisView = new CookThisView(cookThisViewModel, finishController, returnToPreviousViewController);
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
        PreferenceView preferenceView = new PreferenceView(preferenceViewModel,redirectToPreferenceViewModel,preferenceController ,finishController2 );
        views.add(preferenceView, preferenceView.viewName);
        FinishViewModel finishViewModel3 = new FinishViewModel();
        FinishPresenter finishPresenter3 = new FinishPresenter(viewManagerModel,finishViewModel3);
        FinishInteractor finishInteractor3 = new FinishInteractor(finishPresenter3);
        FinishController finishController3 = new FinishController(finishInteractor3);
        FavViewViewModel favViewViewModel = new FavViewViewModel();
        FavViewPresenter favViewPresenter = new FavViewPresenter(favViewViewModel,viewManagerModel);
        FavViewDataAccessInterface favViewDataAccessInterface = new FavRecipeCardApi();
        FavViewInputBoundary favViewInteractor = new FavViewInteractor(favViewDataAccessInterface, favViewPresenter, inMemoryDataAccessUser );
        FavViewController favViewController = new FavViewController(favViewInteractor);
        UnfavouriteThisPresenter unfavouriteThisPresenter = new UnfavouriteThisPresenter(favViewViewModel,viewManagerModel);
        UnfavouriteThisInputBoundary unfavouriteThisInteractor = new UnfavouriteThisInteractor(favViewDataAccessInterface, inMemoryDataAccessUser, unfavouriteThisPresenter);
        UnfavouriteThisController unfavouriteThisController = new UnfavouriteThisController(unfavouriteThisInteractor);
        FavView favView = new FavView(favViewViewModel, finishController3,cookThisController, unfavouriteThisController);
        views.add(favView, favView.viewName);
        GenerateRecipeController generateRecipeController = new GenerateRecipeController(generateRecipeInteractor);
        LogoutPresenter logoutPresenter = new LogoutPresenter(viewManagerModel);
        LogoutInteractor logoutInteractor = new LogoutInteractor(inMemoryDataAccessUser,logoutPresenter);
        LogoutController logoutController = new LogoutController(logoutInteractor);

        MainPageView mainPageView = new MainPageView(generateRecipeController,cookThisOrReRollViewModel,redirectToPreferenceController,favViewController,favViewViewModel,logoutController);

        // Simulate clicks on buttons and check if the controller methods are called
        // Tests work on windows, may not work on mac for buttons (refer to above)
        JButton generateRecipeBtn = getButtonFromPanel(mainPageView, "   Generate Recipe   ");
        generateRecipeBtn.doClick();;

        JButton favouritesBtn = getButtonFromPanel(mainPageView, "   Favourites   ");
        favouritesBtn.doClick();

        JButton preferencesBtn = getButtonFromPanel(mainPageView, "   Preferences   ");
        preferencesBtn.doClick();
    }

    private JButton getButtonFromPanel(MainPageView mainPageView, String buttonText) {
        Component[] components = mainPageView.getComponents();
        for (Component component : components) {
            if (component instanceof JPanel) {
                JPanel panel = (JPanel) component;
                Component[] panelComponents = panel.getComponents();
                for (Component comp : panelComponents) {
                    if (comp instanceof JButton) {
                        JButton button = (JButton) comp;
                        if (button.getText().equals(buttonText)) {
                            return button;
                        }
                    }
                }
            }
        }
        return null;
    }
}
