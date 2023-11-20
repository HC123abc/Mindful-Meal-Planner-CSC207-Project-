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
        StringBuilder intolerances = new StringBuilder();

        Random random = new Random();
        String randomCuisine = "";
        if (!selectedCuisines.isEmpty()) {
            randomCuisine = selectedCuisines.get(random.nextInt(selectedCuisines.size())).toLowerCase();
        }
        tags.append(randomCuisine);

        for (String diet : selectedDiets) {
            tags.append(",").append(diet.toLowerCase());
        }

        // Remove the trailing comma if it exists
        if (tags.length() > 0 && tags.charAt(tags.length() - 1) == ',') {
            tags.deleteCharAt(tags.length() - 1);
        }

        // Remove whitespaces
        String tagsString = tags.toString().trim();
        String intolerancesString = intolerances.toString().trim();

        // Return both tags and intolerances separately
        return tagsString ;
    }
    public String getIntolerances() {
        StringBuilder intolerances = new StringBuilder();

        for (String intolerance : selectedIntolerances) {
            intolerances.append(",").append(intolerance.toLowerCase());
        }

        // Remove the trailing comma if it exists
        if (intolerances.length() > 0 && intolerances.charAt(0) == ',') {
            intolerances.deleteCharAt(0);
        }

        // Remove whitespaces
        return intolerances.toString().trim();
    }

}
