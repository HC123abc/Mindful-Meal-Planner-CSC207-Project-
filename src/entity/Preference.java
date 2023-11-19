package entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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

        Random random = new Random();
        String randomCuisine = "";
        if (!selectedCuisines.isEmpty()) {
            randomCuisine = selectedCuisines.get(random.nextInt(selectedCuisines.size())).toLowerCase();
        }
        tags.append(randomCuisine);

        for (String diet : selectedDiets) {
            tags.append(",").append(diet.toLowerCase());
        }

        for (String intolerance : selectedIntolerances) {
            tags.append(",").append(intolerance.toLowerCase());
        }

        // Remove the trailing comma if it exists
        if (tags.length() > 0 && tags.charAt(tags.length() - 1) == ',') {
            tags.deleteCharAt(tags.length() - 1);
        }
        // Remove whitespaces
        return tags.toString().trim();
    }

}
