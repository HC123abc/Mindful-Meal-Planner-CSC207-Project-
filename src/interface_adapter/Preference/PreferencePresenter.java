package interface_adapter.Preference;


import interface_adapter.CookThisOrReRoll.CookThisOrReRollViewModel;
import interface_adapter.ViewManagerModel;
import use_case.Preference.PreferenceOutputBoundary;

public class PreferencePresenter implements PreferenceOutputBoundary {
    private final PreferenceViewModel preferenceViewModel;
    private ViewManagerModel viewManagerModel;

    public PreferencePresenter( PreferenceViewModel preferenceViewModel, ViewManagerModel viewManagerModel) {
        this.preferenceViewModel = preferenceViewModel;
        this.viewManagerModel = viewManagerModel;
    }
    public void prepareSuccessView(String saved) {
//      main view model
        PreferenceState preferenceState = preferenceViewModel.getState();
        preferenceState.setSaved(saved);
        preferenceViewModel.firePropertyChanged();

    }
}
