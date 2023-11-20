package interface_adapter.Preference;

import java.util.HashMap;
import java.util.Map;

public class PreferenceState {
    private String saved = "";
    private final String[] cusisines = {"African", "Asian", "American", "British", "Cajun", "Caribbean", "Chinese",
            "Eastern European", "European", "French", "German", "Greek", "Indian", "Irish", "Italian",
            "Japanese", "Jewish", "Korean", "Latin American", "Mediterranean", "Mexican",
            "Middle Eastern", "Nordic", "Southern", "Spanish", "Thai", "Vietnamese"};
    private final String[] diet = {"Gluten Free", "Vegetarian", "Vegan", "Pescetarian"};
    private final String[] intolerances = {"Dairy", "Egg", "Gluten", "Grain", "Peanut", "Seafood", "Sesame",
            "Shellfish", "Soy", "Sulfite", "Tree Nut", "Wheat"};

    private Map<String, Boolean> selectedCuisines = new HashMap<>();
    private Map<String, Boolean> selectedDiets = new HashMap<>();
    private Map<String, Boolean> selectedIntolerances = new HashMap<>();

    //  idk if we need but it was in SignUp thing
    public PreferenceState(PreferenceState copy) {
        selectedCuisines = copy.selectedCuisines;
        selectedDiets = copy.selectedDiets;
        selectedIntolerances = copy.selectedIntolerances;
    }

    // default PreferenceState
    public PreferenceState() {
        for (String i : cusisines) {
            selectedCuisines.put(i, false);
        }
        for (String i : diet) {
            selectedDiets.put(i, false);
        }
        for (String i : intolerances) {
            selectedIntolerances.put(i, false);
        }
    }

    public Map<String, Boolean> getSelectedCuisines() {
        return selectedCuisines;
    }

    public void setSelectedCuisines(String type, Boolean selected) {
        selectedCuisines.replace(type, selected);
    }

    public Map<String, Boolean> getSelectedDiets() {
        return selectedDiets;
    }

    public void setSelectedDiets(String type, Boolean selected) {
        selectedDiets.replace(type, selected);
    }

    public Map<String, Boolean> getSelectedIntolerances() {
        return selectedIntolerances;
    }

    public void setSelectedIntolerances(String type, Boolean selected) {
        selectedIntolerances.replace(type, selected);
    }

    public String getSaved() {
        return saved;
    }

    public void setSaved(String saved) {
        this.saved = saved;
    }
    public void setSelectedCuisinesMap(Map<String, Boolean> selectedCuisinesMap) {
        this.selectedCuisines = selectedCuisinesMap;
    }
    public void setSelectedDietsMap(Map<String, Boolean> selectedDietsMap) {
        this.selectedDiets = selectedDietsMap;
    }
    public void setSelectedIntolerancesMap(Map<String, Boolean> selectedIntolerancesMap) {
        this.selectedIntolerances = selectedIntolerancesMap;
    }

}

