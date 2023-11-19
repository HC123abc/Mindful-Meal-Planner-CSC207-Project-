package interface_adapter.RedirectToPreference;

import use_case.Preference.PreferenceInputBoundary;
import use_case.Preference.PreferenceInputData;
import use_case.RedirectToPreference.RedirectToPreferenceInputBoundary;

import java.util.Map;

public class RedirectToPreferenceController {

    final RedirectToPreferenceInputBoundary redirectToPreferenceInteractor;

    public RedirectToPreferenceController(RedirectToPreferenceInputBoundary redirectToPreferenceInteractor) {
        this.redirectToPreferenceInteractor = redirectToPreferenceInteractor;
    }

    public void execute() {

        redirectToPreferenceInteractor.execute();
    }
}
