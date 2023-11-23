package interface_adapter.CookThis;

import interface_adapter.CookThis.CookThisPresenter;
import interface_adapter.CookThis.CookThisViewModel;
import interface_adapter.ViewManagerModel;
import org.junit.jupiter.api.Test;
import use_case.cookThis.CookThisOutputData;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CookThisPresenterTest {

    @Test
    void prepareSuccessViewTest() {

        CookThisViewModel mockViewModel = new CookThisViewModel();
        ViewManagerModel mockViewManagerModel = new ViewManagerModel();

        // Creating CookThisPresenter instance
        CookThisPresenter presenter = new CookThisPresenter(mockViewModel, mockViewManagerModel);

        // Creating CookThisOutputData
        Map<String,String> ingredient = new HashMap<>();
        ingredient.put("Ingredient A", "ImageURL_A");
        ingredient.put("Ingredient B", "ImageURL_B");
        CookThisOutputData outputData = new CookThisOutputData(ingredient,"1. add this","");

        // Invoking the method to be tested
        presenter.prepareSuccessView(outputData);

        // Verifying if viewManagerModel.setActiveView is equal to "CookThis"
        // Replace this assertion with the actual method used to get the active view from the ViewManagerModel
        assertEquals("CookThis", mockViewManagerModel.getActiveView());
        CookThisState cookThisState = mockViewModel.getState();
        assertEquals(ingredient, cookThisState.getIngredientsWithImage());
        assertEquals("1. add this", cookThisState.getInstruction());
    }
}
