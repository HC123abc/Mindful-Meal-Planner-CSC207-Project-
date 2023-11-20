package interface_adapter.RedirectToPreference;


import interface_adapter.CookThisOrReRoll.CookThisOrReRollState;
import interface_adapter.Preference.PreferenceState;
import interface_adapter.Preference.PreferenceViewModel;
import interface_adapter.ViewManagerModel;
import use_case.Preference.PreferenceOutputBoundary;
import use_case.RedirectToPreference.RedirectToPreferenceOutputBoundary;
import use_case.RedirectToPreference.RedirectToPreferenceOutputData;

public class RedirectToPreferencePresenter implements RedirectToPreferenceOutputBoundary {
    private final RedirectToPreferenceViewModel redirectToPreferenceViewModel;
    private ViewManagerModel viewManagerModel;

    public RedirectToPreferencePresenter(RedirectToPreferenceViewModel redirectToPreferenceViewModel, ViewManagerModel viewManagerModel) {
        this.redirectToPreferenceViewModel = redirectToPreferenceViewModel;
        this.viewManagerModel = viewManagerModel;
    }
    public void prepareSuccessView(RedirectToPreferenceOutputData preferenceOutputdata) {
//      main view model
        PreferenceState preferenceState = redirectToPreferenceViewModel.getState();
        preferenceState.setSelectedCuisinesMap(preferenceOutputdata.getSelectedCusines());
        preferenceState.setSelectedDietsMap(preferenceOutputdata.getSelectedDiet());
        preferenceState.setSelectedIntolerancesMap(preferenceOutputdata.getSelectedIntolerance());
        this.redirectToPreferenceViewModel.setState(preferenceState);
        redirectToPreferenceViewModel.firePropertyChanged();
        viewManagerModel.setActiveView(redirectToPreferenceViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
