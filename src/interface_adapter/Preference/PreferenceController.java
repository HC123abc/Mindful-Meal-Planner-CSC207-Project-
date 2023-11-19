package interface_adapter.Preference;

import use_case.Preference.PreferenceInputBoundary;
import use_case.Preference.PreferenceInputData;
import use_case.reRoll.ReRollInputBoundary;

import java.util.Map;

public class PreferenceController {

    final PreferenceInputBoundary preferenceInteractor;

    public PreferenceController(PreferenceInputBoundary preferenceInteractor) {
        this.preferenceInteractor = preferenceInteractor;
    }

    public void execute(Map<String, Boolean> selectedCusines, Map<String, Boolean> selectedDiet, Map<String, Boolean> selectedIntolerance) {
        PreferenceInputData inputData = new PreferenceInputData(selectedCusines,selectedDiet,selectedIntolerance);
        preferenceInteractor.execute(inputData);
    }
}
