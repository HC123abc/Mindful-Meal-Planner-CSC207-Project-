package interface_adapter.RedirectToPreference;

import static org.junit.Assert.*;

import interface_adapter.Preference.PreferenceState;
import interface_adapter.ViewManagerModel;
import org.junit.Before;
import org.junit.Test;
import use_case.RedirectToPreference.RedirectToPreferenceOutputData;

import java.util.HashMap;
import java.util.Map;

public class RedirectToPreferencePresenterTest {
    private RedirectToPreferenceViewModel viewModel;
    private ViewManagerModel viewManagerModel;
    private RedirectToPreferencePresenter presenter;

    @Before
    public void setUp() {
        viewModel = new RedirectToPreferenceViewModel();
        viewManagerModel = new ViewManagerModel();
        presenter = new RedirectToPreferencePresenter(viewModel, viewManagerModel);
    }

    @Test
    public void testPrepareSuccessView() {
        // Mock data for the output from RedirectToPreference use case
        Map<String, Boolean> selectedCuisines = new HashMap<>();
        selectedCuisines.put("Italian", true);
        selectedCuisines.put("Indian", true);

        Map<String, Boolean> selectedDiets = new HashMap<>();
        selectedDiets.put("Vegetarian", true);
        selectedDiets.put("Vegan", true);

        Map<String, Boolean> selectedIntolerances = new HashMap<>();
        selectedIntolerances.put("Dairy", true);
        selectedIntolerances.put("Gluten", true);

        RedirectToPreferenceOutputData preferenceOutputData = new RedirectToPreferenceOutputData(
                selectedCuisines, selectedDiets, selectedIntolerances
        );

        // Simulate preparing the success view
        presenter.prepareSuccessView(preferenceOutputData);

        // Check if the ViewModel and ViewManagerModel were updated
        PreferenceState preferenceState = viewModel.getState();
        Map<String, Boolean> cuisines = preferenceState.getSelectedCuisines();
        Map<String, Boolean> diets = preferenceState.getSelectedDiets();
        Map<String, Boolean> intolerances = preferenceState.getSelectedIntolerances();

        // Assert the changes in ViewModel state
        assertTrue(cuisines.containsKey("Italian") && cuisines.get("Italian"));
        assertTrue(cuisines.containsKey("Indian") && cuisines.get("Indian"));

        assertTrue(diets.containsKey("Vegetarian") && diets.get("Vegetarian"));
        assertTrue(diets.containsKey("Vegan") && diets.get("Vegan"));

        assertTrue(intolerances.containsKey("Dairy") && intolerances.get("Dairy"));
        assertTrue(intolerances.containsKey("Gluten") && intolerances.get("Gluten"));

        // Check if the ViewManagerModel was updated
        assertEquals("Preference", viewManagerModel.getActiveView());
    }
}
