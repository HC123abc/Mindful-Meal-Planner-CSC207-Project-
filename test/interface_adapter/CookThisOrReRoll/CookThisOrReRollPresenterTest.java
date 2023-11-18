package interface_adapter.CookThisOrReRoll;

import interface_adapter.ViewManagerModel;
import org.junit.jupiter.api.Test;
import use_case.generateRecipe.GenerateRecipeOutputData;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CookThisOrReRollPresenterTest {

    @Test
    void prepareSuccessViewTest() {
        // Mock ViewModel and ViewManagerModel
        CookThisOrReRollViewModel mockViewModel = new CookThisOrReRollViewModel();
        ViewManagerModel mockViewManagerModel = new ViewManagerModel();

        // Creating CookThisOrReRollPresenter instance
        CookThisOrReRollPresenter presenter = new CookThisOrReRollPresenter(mockViewModel, mockViewManagerModel);

        // Creating GenerateRecipeOutputData
        GenerateRecipeOutputData outputData = new GenerateRecipeOutputData("Test Recipe","25","4", "Test Summary", "https//A: Ingredient A 10, https//B: Ingredient B 30","Step 1: Do something","https://example.com/test.jpg","231");

        // Invoking the method to be tested
        presenter.prepareSuccessView(outputData);

        // Verifying if viewManagerModel.setActiveView is equal to "CookThisOrReRoll"
        assertEquals("CookThisOrReRoll", mockViewManagerModel.getActiveView());
        // Asserting the ViewModel state after invoking prepareSuccessView
        CookThisOrReRollState cookThisOrReRollState = mockViewModel.getState();
        assertEquals("Test Recipe", cookThisOrReRollState.getTitle());
        assertEquals("Test Summary", cookThisOrReRollState.getSummary());
        assertEquals("https://example.com/test.jpg", cookThisOrReRollState.getImage());
        assertEquals("4", cookThisOrReRollState.getServings());
        assertEquals("25", cookThisOrReRollState.getReadyInMinutes());
        assertEquals("https//A: Ingredient A 10, https//B: Ingredient B 30", cookThisOrReRollState.getIngredients());
        assertEquals("Step 1: Do something", cookThisOrReRollState.getInstruction());
        assertEquals("231", cookThisOrReRollState.getId());

    }

    @Test
    void prepareFailViewTest() {
        // Mock ViewModel
        CookThisOrReRollViewModel mockViewModel = new CookThisOrReRollViewModel();
        ViewManagerModel mockViewManagerModel = new ViewManagerModel();

        // Creating CookThisOrReRollPresenter instance
        CookThisOrReRollPresenter presenter = new CookThisOrReRollPresenter(mockViewModel, mockViewManagerModel);

        // Invoking the method to be tested
        presenter.prepareFailView("Error: Recipe not found");

        // Verifying if the error message is set in the ViewModel
        assertEquals("Error: Recipe not found", mockViewModel.getState().getRecipeError());
    }
}
