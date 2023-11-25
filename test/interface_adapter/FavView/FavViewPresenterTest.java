package interface_adapter.FavView;

import use_case.FavView.FavViewOutputData;
import interface_adapter.ViewManagerModel;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class FavViewPresenterTest {

    private FavViewViewModel viewModel;
    private ViewManagerModel viewManagerModel;
    private FavViewPresenter presenter;

    @Before
    public void setUp() {
        viewModel = new FavViewViewModel();
        viewManagerModel = new ViewManagerModel();
        presenter = new FavViewPresenter(viewModel, viewManagerModel);
    }

    @Test
    public void testPrepareSuccessView() {
        ViewManagerModel mockViewManagerModel = new ViewManagerModel();
        Map<String, String> recipeDetailsMap = new HashMap<>();
        recipeDetailsMap.put("title", "Recipe Title");
        recipeDetailsMap.put("id", "12345");
        recipeDetailsMap.put("readyInMinutes", "30");
        recipeDetailsMap.put("servings", "4");
        recipeDetailsMap.put("summary", "This is a summary");
        recipeDetailsMap.put("extendedIngredients", "This is a extendedIngredients");
        recipeDetailsMap.put("extendedInstructions", "This is a extendedInstructions");
        recipeDetailsMap.put("recipeImageURL", "This is a recipeImageURL");
        recipeDetailsMap.put("isFavourite", Boolean.toString(false));

        Map<String, Map<String, String>> recipeData = new HashMap<>();
        recipeData.put("recipe1", recipeDetailsMap);

        FavViewOutputData outputData = new FavViewOutputData(recipeData);

        presenter.prepareSuccessView(outputData);

        FavViewState state = viewModel.getState();

        assertEquals(1, state.getRecipeWithCardImage().size());
        Map<String, String> retrievedRecipeAttributes = state.getRecipeWithCardImage().get("recipe1");
        assertEquals("Recipe Title", retrievedRecipeAttributes.get("title"));
        assertEquals("12345", retrievedRecipeAttributes.get("id"));
        assertEquals("30", retrievedRecipeAttributes.get("readyInMinutes"));
        assertEquals("4", retrievedRecipeAttributes.get("servings"));
        assertEquals("This is a summary", retrievedRecipeAttributes.get("summary"));
    }

    @Test
    public void testPrepareFailView() {
        presenter.prepareFailView("Error occurred");

        FavViewState state = viewModel.getState();

        assertEquals("Error occurred", state.getError());

    }
}
