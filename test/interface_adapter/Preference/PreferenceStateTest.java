package interface_adapter.Preference;


import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class PreferenceStateTest {
    @Test
    public void testPreferenceStateChangeMap() {

        // Arrange
        PreferenceState preferenceState = new PreferenceState();

        Map<String, Boolean> selectedCuisines = new HashMap<>();
        selectedCuisines.put("Italian", true);

        Map<String, Boolean> selectedDiets = new HashMap<>();
        selectedDiets.put("Vegetarian", true);
        selectedDiets.put("Vegan", true);

        Map<String, Boolean> selectedIntolerances = new HashMap<>();
        selectedIntolerances.put("Dairy", true);
        selectedIntolerances.put("Gluten", true);
        preferenceState.setSelectedCuisinesMap(selectedCuisines);
        preferenceState.setSelectedDietsMap(selectedDiets);
        preferenceState.setSelectedIntolerancesMap(selectedIntolerances);
        assertEquals(selectedCuisines, preferenceState.getSelectedCuisines());
        assertEquals(selectedDiets, preferenceState.getSelectedDiets());
        assertEquals(selectedIntolerances, preferenceState.getSelectedIntolerances());

    }
    @Test
    public void testPreferenceStateAttributes() {
        // Arrange
        PreferenceState preferenceState = new PreferenceState();

        // Act
        Map<String, Boolean> selectedCuisines = preferenceState.getSelectedCuisines();
        Map<String, Boolean> selectedDiets = preferenceState.getSelectedDiets();
        Map<String, Boolean> selectedIntolerances = preferenceState.getSelectedIntolerances();
        String saved = preferenceState.getSaved();

        // Assert
        assertNotNull(selectedCuisines);
        assertNotNull(selectedDiets);
        assertNotNull(selectedIntolerances);
        assertEquals("", saved);

        // Verify default values
        assertFalse(selectedCuisines.get("Italian"));
        assertFalse(selectedDiets.get("Vegetarian"));
        assertFalse(selectedIntolerances.get("Dairy"));

        // Modify values and test setters
        preferenceState.setSelectedCuisines("Italian", true);
        preferenceState.setSelectedDiets("Vegetarian", true);
        preferenceState.setSelectedIntolerances("Dairy", true);

        // Re-access and test the modified values
        assertTrue(selectedCuisines.get("Italian"));
        assertTrue(selectedDiets.get("Vegetarian"));
        assertTrue(selectedIntolerances.get("Dairy"));
    }
}