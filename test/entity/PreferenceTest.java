package entity;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;

public class PreferenceTest {
    private Preference preference;

    @BeforeEach
    void setUp() {
        preference = new Preference();
    }

    @Test
    void testGetSelectedCuisines() {
        List<String> cuisines = Arrays.asList("African", "Chinese", "Italian");
        preference.setSelectedCuisines(cuisines);

        List<String> selectedCuisines = preference.getSelectedCuisines();

        assertEquals(cuisines, selectedCuisines);
    }

    @Test
    void testGetSelectedDiets() {
        List<String> diets = Arrays.asList("Gluten Free", "Vegetarian");
        preference.setSelectedDiets(diets);

        List<String> selectedDiets = preference.getSelectedDiets();

        assertEquals(diets, selectedDiets);
    }

    @Test
    void testGetSelectedIntolerances() {
        List<String> intolerances = Arrays.asList("Dairy", "Egg", "Gluten");
        preference.setSelectedIntolerances(intolerances);

        List<String> selectedIntolerances = preference.getSelectedIntolerances();

        assertEquals(intolerances, selectedIntolerances);
    }
    @Test
    void testGetTags() {
        // Prepare test data
        List<String> cuisines = Arrays.asList("African", "Chinese", "Italian");
        List<String> diets = Arrays.asList("Gluten Free", "Vegetarian");
        preference.setSelectedCuisines(cuisines);
        preference.setSelectedDiets(diets);

        // Get tags
        String tags = preference.getTags();

        // Validate tags
        assertTrue(tags.contains("african") ||tags.contains("chinese")||tags.contains("italian"));
        assertTrue(tags.contains("gluten free"));
        assertTrue(tags.contains("vegetarian"));

        // Validate comma-separated tags
        int commaCount = tags.length() - tags.replace(",", "").length();
        assertEquals(2, commaCount); // There should be 2 commas separating tags
    }

    @Test
    void testGetIntolerances() {
        // Prepare test data
        List<String> intolerances = Arrays.asList("Dairy", "Egg", "Gluten");
        preference.setSelectedIntolerances(intolerances);

        // Get intolerances
        String result = preference.getIntolerances();

        // Validate intolerances
        assertTrue(result.contains("dairy"));
        assertTrue(result.contains("egg"));
        assertTrue(result.contains("gluten"));

        // Validate comma-separated intolerances
        int commaCount = result.length() - result.replace(",", "").length();
        assertEquals(2, commaCount); // There should be 2 commas separating intolerances
    }
}
