package entity;

import java.util.ArrayList;
import java.util.List;

public class Preference {
    private List<String> selectedCuisines = new ArrayList<>();
    private List<String> selectedDiets = new ArrayList<>();
    private List<String> selectedIntolerances = new ArrayList<>();

    // Constructors: idk if default emtpy or stuff like loading... how we do that
    // getters, and setters (if needed)

    public List<String> getSelectedCuisines() {
        return selectedCuisines;
    }

    public void setSelectedCuisines(List<String> selectedCuisines) {
        this.selectedCuisines = selectedCuisines;
    }

    public List<String> getSelectedDiets() {
        return selectedDiets;
    }

    public void setSelectedDiets(List<String> selectedDiets) {
        this.selectedDiets = selectedDiets;
    }

    public List<String> getSelectedIntolerances() {
        return selectedIntolerances;
    }

    public void setSelectedIntolerances(List<String> selectedIntolerances) {
        this.selectedIntolerances = selectedIntolerances;
    }
    public String getTags() {
        StringBuilder tags = new StringBuilder();

        if (!selectedCuisines.isEmpty()) {
            for (String cuisine : selectedCuisines) {
                tags.append(cuisine).append(",");
            }
        }

        if (!selectedDiets.isEmpty()) {
            for (String diet : selectedDiets) {
                tags.append(diet).append(",");
            }
        }

        if (!selectedIntolerances.isEmpty()) {
            for (String intolerance : selectedIntolerances) {
                tags.append(intolerance).append(",");
            }
        }
//      remove whitespaces so it dont mess up our query
        return tags.toString().trim();
    }
}
