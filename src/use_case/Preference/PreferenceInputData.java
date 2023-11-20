package use_case.Preference;

import java.util.Map;

public class PreferenceInputData {
    private Map<String, Boolean> selectedCusines;
    private Map<String, Boolean> selectedDiet;
    private Map<String, Boolean> selectedIntolerance;
    public PreferenceInputData(Map<String, Boolean> selectedCusines, Map<String, Boolean> selectedDiet, Map<String, Boolean> selectedIntolerance) {
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

