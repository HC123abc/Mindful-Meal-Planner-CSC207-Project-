package interface_adapter.RedirectToPreference;


import interface_adapter.CookThisOrReRoll.CookThisOrReRollState;
import interface_adapter.Preference.PreferenceState;
import interface_adapter.Preference.PreferenceViewModel;
import interface_adapter.ViewManagerModel;
import use_case.Preference.PreferenceOutputBoundary;
import use_case.RedirectToPreference.RedirectToPreferenceOutputBoundary;
import use_case.RedirectToPreference.RedirectToPreferenceOutputData;

public class RedirectToPreferencePresenter implements RedirectToPreferenceOutputBoundary {
    private final RedirectToPreferenceViewModel preferenceViewModel;
    private ViewManagerModel viewManagerModel;

    public RedirectToPreferencePresenter(RedirectToPreferenceViewModel preferenceViewModel, ViewManagerModel viewManagerModel) {
        this.preferenceViewModel = preferenceViewModel;
        this.viewManagerModel = viewManagerModel;
    }
    public void prepareSuccessView(RedirectToPreferenceOutputData preferenceOutputdata) {
//      main view model
        PreferenceState preferenceState = preferenceViewModel.getState();
        preferenceState.setSelectedCuisinesMap(preferenceOutputdata.getSelectedCusines());
        preferenceState.setSelectedDietsMap(preferenceOutputdata.getSelectedDiet());
        preferenceState.setSelectedIntolerancesMap(preferenceOutputdata.getSelectedIntolerance());
        this.preferenceViewModel.setState(preferenceState);
        preferenceViewModel.firePropertyChanged();
        viewManagerModel.setActiveView(preferenceViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
