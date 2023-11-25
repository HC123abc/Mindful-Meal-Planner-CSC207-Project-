package use_case.RedirectToPreference;

import data_access.InMemoryDataAccess.InMemoryDataAccessUser;
import data_access.InMemoryDataAccess.InMemoryDataAccessUserInterface;
import entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import use_case.RedirectToPreference.RedirectToPreferenceInputBoundary;
import use_case.RedirectToPreference.RedirectToPreferenceInteractor;
import use_case.RedirectToPreference.RedirectToPreferenceOutputBoundary;
import use_case.RedirectToPreference.RedirectToPreferenceOutputData;
import entity.Preference;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RedirectToPreferenceInteractorTest {

    private RedirectToPreferenceInteractor redirectToPreferenceInteractor;
    private InMemoryDataAccessUserInterface inMemoryDataAccessUser;
    private RedirectToPreferenceOutputBoundary redirectToPreferenceOutputPresenter;

    @BeforeEach
    void setUp() {
        inMemoryDataAccessUser = new InMemoryDataAccessUser();
        redirectToPreferenceOutputPresenter = new RedirectToPreferenceOutputBoundary() {
            @Override
            public void prepareSuccessView(RedirectToPreferenceOutputData outputData) {
                // Verify that the presenter was called with the correct output data
                Map<String, Boolean> outputCuisines = outputData.getSelectedCusines();
                Map<String, Boolean> outputDiets = outputData.getSelectedDiet();
                Map<String, Boolean> outputIntolerances = outputData.getSelectedIntolerance();

                assertTrue(outputCuisines.containsKey("Italian") && outputCuisines.get("Italian"));
                assertTrue(outputCuisines.containsKey("Indian") && outputCuisines.get("Indian"));
                assertTrue(outputDiets.containsKey("Vegetarian") && outputDiets.get("Vegetarian"));
                assertTrue(outputDiets.containsKey("Vegan") && outputDiets.get("Vegan"));
                assertTrue(outputIntolerances.containsKey("Dairy") && outputIntolerances.get("Dairy"));
                assertTrue(outputIntolerances.containsKey("Gluten") && outputIntolerances.get("Gluten"));

            }
        };
        redirectToPreferenceInteractor = new RedirectToPreferenceInteractor(inMemoryDataAccessUser, redirectToPreferenceOutputPresenter);
    }

    @Test
    void testRedirectToPreferenceInteractorExecution() {
        // Prepare user and preference with selected cuisines, diets, and intolerances
        List<String> selectedCuisines = Arrays.asList("Italian", "Indian");
        List<String> selectedDiets = Arrays.asList("Vegetarian", "Vegan");
        List<String> selectedIntolerances = Arrays.asList("Dairy", "Gluten");

        Preference preference = new Preference();
        preference.setSelectedCuisines(selectedCuisines);
        preference.setSelectedDiets(selectedDiets);
        preference.setSelectedIntolerances(selectedIntolerances);

        User user = new User("test","test");
        user.setPreference(preference);

        inMemoryDataAccessUser.setActiveUser(user);
        // Execute the interactor
        redirectToPreferenceInteractor.execute();
    }
}
