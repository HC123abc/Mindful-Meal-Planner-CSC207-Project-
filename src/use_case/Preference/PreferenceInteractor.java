package use_case.Preference;

import data_access.InMemoryDataAccess.InMemoryDataAccessUserInterface;
import entity.*;
import use_case.reRoll.ReRollInputBoundary;
import use_case.reRoll.ReRollOutputBoundary;
import use_case.reRoll.ReRollOutputData;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class PreferenceInteractor implements PreferenceInputBoundary {
    private Preference preference;
    private User user;

    private PreferenceOutputBoundary preferencePresenter;

    public PreferenceInteractor(InMemoryDataAccessUserInterface inMemoryDataAccessUser, PreferenceOutputBoundary preferencePresenter) {
        this.user = inMemoryDataAccessUser.getActiveUser();
        this.preference = user.getPreference();
        this.preferencePresenter = preferencePresenter;
    }

    @Override
    public void execute(PreferenceInputData input) {
        List<String> selectedCuisines = convertMapToList(input.getSelectedCusines());
        List<String> selectedDiets = convertMapToList(input.getSelectedDiet());
        List<String> selectedIntolerances = convertMapToList(input.getSelectedIntolerance());

        // Update the preference entity
        this.preference.setSelectedCuisines(selectedCuisines);
        this.preference.setSelectedDiets(selectedDiets);
        this.preference.setSelectedIntolerances(selectedIntolerances);
        System.out.println(this.user.getPreference().getSelectedCuisines());
        System.out.println(this.user.getPreference());

        // Create output data and use the presenter
        this.preferencePresenter.prepareSuccessView("Successfully updated your preferences!");
    }

    // Utility method to convert Map<String, Boolean> to List<String>
    private List<String> convertMapToList(Map<String, Boolean> map) {
        List<String> list = new ArrayList<>();
        for (Map.Entry<String, Boolean> entry : map.entrySet()) {
            if (entry.getValue()) {
                list.add(entry.getKey());
            }
        }
        return list;
    }

}
