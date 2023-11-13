package interface_adapter.Preference;

import java.util.HashMap;
import java.util.Map;

public class PreferenceState {
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
//      default all False
        for (String i : this.cusisines) {
            this.selectedCuisines.put(i, false);
        }
        for (String i : this.diet) {
            this.selectedDiets.put(i, false);
        }
        for (String i : this.intolerances) {
            this.selectedIntolerances.put(i, false);
        }
    }
//  load one
//    public PreferenceState(textfile) {
////      default all False
//        for (String i : this.cusisines) {
//            this.selectedCuisines.put(i, false);
//        }
//        for (String i : this.diet) {
//            this.selectedDiets.put(i, false);
//        }
//        for (String i : this.intolerances) {
//            this.selectedIntolerances.put(i, false);
//        }

    public Map<String, Boolean> getSelectedCuisines() {
        return selectedCuisines;
    }

    public void setSelectedCuisines(String type, Boolean selected) {
        selectedCuisines.replace(type, selected);
    }

    public Map<String, Boolean> getSelectedDiets() {
        return selectedDiets;
    }

    public void setSelectedDiets(Map<String, Boolean> selectedDiets) {
        this.selectedDiets = selectedDiets;
    }

    public Map<String, Boolean> getSelectedIntolerances() {
        return selectedIntolerances;
    }

    public void setSelectedIntolerances(Map<String, Boolean> selectedIntolerances) {
        this.selectedIntolerances = selectedIntolerances;
    }
}

