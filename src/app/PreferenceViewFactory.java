package app;

import data_access.InMemoryDataAccess.InMemoryDataAccessUserInterface;
import interface_adapter.Finish.FinishController;
import interface_adapter.Finish.FinishPresenter;
import interface_adapter.Finish.FinishViewModel;
import interface_adapter.Preference.PreferenceController;
import interface_adapter.Preference.PreferencePresenter;
import interface_adapter.Preference.PreferenceViewModel;
import interface_adapter.RedirectToPreference.RedirectToPreferenceController;
import interface_adapter.RedirectToPreference.RedirectToPreferencePresenter;
import interface_adapter.RedirectToPreference.RedirectToPreferenceViewModel;
import interface_adapter.ViewManagerModel;
import use_case.Finish.FinishInteractor;
import use_case.Preference.PreferenceInteractor;
import use_case.RedirectToPreference.RedirectToPreferenceInteractor;
import view.PreferenceView;

public class PreferenceViewFactory {
    public static PreferenceView createView(ViewManagerModel viewManagerModel, InMemoryDataAccessUserInterface inMemoryDataAccessUser, RedirectToPreferenceViewModel redirectToPreferenceViewModel){

        FinishViewModel finishViewModel2 = new FinishViewModel();
        FinishPresenter finishPresenter2 = new FinishPresenter(viewManagerModel,finishViewModel2);
        FinishInteractor finishInteractor2 = new FinishInteractor(finishPresenter2);
        FinishController finishController2 = new FinishController(finishInteractor2);

        PreferenceViewModel preferenceViewModel = new PreferenceViewModel();
        PreferencePresenter preferencePresenter = new PreferencePresenter(preferenceViewModel,viewManagerModel);
        PreferenceInteractor preferenceInteractor = new PreferenceInteractor(inMemoryDataAccessUser,preferencePresenter);
        PreferenceController preferenceController = new PreferenceController(preferenceInteractor);
        PreferenceView preferenceView = new PreferenceView(preferenceViewModel,redirectToPreferenceViewModel, preferenceController ,finishController2 );
        return preferenceView;
    }
}
