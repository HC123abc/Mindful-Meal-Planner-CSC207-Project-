package use_case.RedirectToPreference;

import data_access.InMemoryDataAccess.InMemoryDataAccessUserInterface;
import entity.Preference;
import entity.RecipeFactory;
import entity.User;
import use_case.Preference.PreferenceInputBoundary;
import use_case.Preference.PreferenceInputData;
import use_case.Preference.PreferenceOutputBoundary;
import use_case.reRoll.ReRollOutputBoundary;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class RedirectToPreferenceInteractor implements RedirectToPreferenceInputBoundary {
    private Preference preference;
    private User user;

    private RedirectToPreferenceOutputBoundary redirectToPreferenceOutputPresenter;

    public RedirectToPreferenceInteractor(InMemoryDataAccessUserInterface inMemoryDataAccessUser, RedirectToPreferenceOutputBoundary redirectToPreferenceOutputPresenter) {
        this.user = inMemoryDataAccessUser.getActiveUser();
        this.preference = user.getPreference();
        this.redirectToPreferenceOutputPresenter = redirectToPreferenceOutputPresenter;
    }

    @Override
    public void execute() {
        List<String> selectedCuisines = this.preference.getSelectedCuisines();
        List<String> selectedDiets = this.preference.getSelectedDiets();
        List<String> selectedIntolerances = this.preference.getSelectedIntolerances();
        String[] cusisines = {"African", "Asian", "American", "British", "Cajun", "Caribbean", "Chinese",
                "Eastern European", "European", "French", "German", "Greek", "Indian", "Irish", "Italian",
                "Japanese", "Jewish", "Korean", "Latin American", "Mediterranean", "Mexican",
                "Middle Eastern", "Nordic", "Southern", "Spanish", "Thai", "Vietnamese"};
        String[] diet = {"Gluten Free", "Vegetarian", "Vegan", "Pescetarian"};
        String[] intolerances = {"Dairy", "Egg", "Gluten", "Grain", "Peanut", "Seafood", "Sesame",
                "Shellfish", "Soy", "Sulfite", "Tree Nut", "Wheat"};

        Map<String, Boolean> selectedCuisinesMap = new HashMap<>();
        Map<String, Boolean> selectedDietsMap = new HashMap<>();
        Map<String, Boolean> selectedIntolerancesMap = new HashMap<>();

        for (String i : cusisines) {
            if (selectedCuisines.contains(i)) {
                selectedCuisinesMap.put(i, true);
            }
            else {
                selectedCuisinesMap.put(i, false);
            }
        }
        for (String i : diet) {
            if (selectedDiets.contains(i)) {
                selectedDietsMap.put(i, true);
            }
            else {
                selectedDietsMap.put(i, false);
            }
        }
        for (String i : intolerances) {
            if (selectedIntolerances.contains(i)) {
                selectedIntolerancesMap.put(i, true);
            }
            else {
                selectedIntolerancesMap.put(i, false);
            }
        }
        RedirectToPreferenceOutputData redirectToPreferenceOutputData = new RedirectToPreferenceOutputData(
                selectedCuisinesMap, selectedDietsMap, selectedIntolerancesMap
        );
        // Create output data and use the presenter
        this.redirectToPreferenceOutputPresenter.prepareSuccessView(redirectToPreferenceOutputData);
    }
}
