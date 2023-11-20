package use_case.RedirectToPreference;

import java.util.Map;

public class RedirectToPreferenceOutputData {

    private Map<String, Boolean> selectedCusines;
    private Map<String, Boolean> selectedDiet;
    private Map<String, Boolean> selectedIntolerance;
    public RedirectToPreferenceOutputData(Map<String, Boolean> selectedCusines, Map<String, Boolean> selectedDiet, Map<String, Boolean> selectedIntolerance) {
        this.selectedCusines = selectedCusines;
        this.selectedDiet = selectedDiet;
        this.selectedIntolerance = selectedIntolerance;
    }

    public Map<String, Boolean> getSelectedCusines() {
        return selectedCusines;
    }

    public Map<String, Boolean> getSelectedDiet() {
        return selectedDiet;
    }

    public Map<String, Boolean> getSelectedIntolerance() {
        return selectedIntolerance;
    }
}
