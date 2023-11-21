package use_case.Preference;

import data_access.InMemoryDataAccess.InMemoryDataAccessUser;
import data_access.InMemoryDataAccess.InMemoryDataAccessUserInterface;
import entity.Preference;
import entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import use_case.Preference.PreferenceInputData;
import use_case.Preference.PreferenceInteractor;
import use_case.Preference.PreferenceOutputBoundary;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PreferenceInteractorTest {

    private PreferenceInteractor preferenceInteractor;
    private InMemoryDataAccessUserInterface inMemoryDataAccessUser;
    private PreferenceOutputBoundary preferencePresenter;

    @BeforeEach
    void setUp() {
        inMemoryDataAccessUser = new InMemoryDataAccessUser();
        preferencePresenter = new PreferenceOutputBoundary() {
            @Override
            public void prepareSuccessView(String message) {
                assertEquals("Successfully updated your preferences!", message);
            }
        };
        preferenceInteractor = new PreferenceInteractor(inMemoryDataAccessUser, preferencePresenter);
    }

    @Test
    void testPreferenceInteractorExecution() {
        // Prepare user and preference
        User user = new User("test","test");
        Preference preference = new Preference();

        // Prepare input data with selected preferences
        Map<String, Boolean> selectedCuisines = new HashMap<>();
        selectedCuisines.put("Italian", true);

        Map<String, Boolean> selectedDiets = new HashMap<>();
        selectedDiets.put("Vegetarian", true);
        selectedDiets.put("Vegan", true);

        Map<String, Boolean> selectedIntolerances = new HashMap<>();
        selectedIntolerances.put("Dairy", true);
        selectedIntolerances.put("Gluten", true);

        PreferenceInputData inputData = new PreferenceInputData(selectedCuisines, selectedDiets, selectedIntolerances);
        user.setPreference(preference);

        inMemoryDataAccessUser.setActiveUser(user);
        // Execute the interactor
        preferenceInteractor.execute(inputData);
        ArrayList<String> cuisines = new ArrayList<>();
        cuisines.add("Italian");
        ArrayList<String> diet = new ArrayList<>();
        diet.add("Vegetarian");
        diet.add("Vegan");
        ArrayList<String> intolerance = new ArrayList<>();
        intolerance.add("Dairy");
        intolerance.add("Gluten");

        // Verify that user preferences were updated and presenter was called with success message
        assertEquals(cuisines, user.getPreference().getSelectedCuisines());
        assertEquals(diet, user.getPreference().getSelectedDiets());
        assertEquals(intolerance, user.getPreference().getSelectedIntolerances());


    }
}
