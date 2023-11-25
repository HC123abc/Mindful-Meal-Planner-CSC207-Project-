package interface_adapter.ReRoll;

import static org.junit.Assert.*;

import interface_adapter.CookThisOrReRoll.CookThisOrReRollState;
import interface_adapter.CookThisOrReRoll.CookThisOrReRollViewModel;
import interface_adapter.ViewManagerModel;
import org.junit.Before;
import org.junit.Test;
import use_case.reRoll.ReRollOutputData;

import java.util.ArrayList;
import java.util.List;

public class ReRollPresenterTest {
    private CookThisOrReRollViewModel viewModel;
    private ViewManagerModel viewManagerModel;
    private ReRollPresenter presenter;

    @Before
    public void setUp() {
        viewModel = new CookThisOrReRollViewModel();
        viewManagerModel = new ViewManagerModel();
        presenter = new ReRollPresenter(viewModel, viewManagerModel);
    }

    @Test
    public void testPrepareSuccessView() {

        ReRollOutputData outputData = new ReRollOutputData(
                "New Recipe", "30",
                "4", "This is a new recipe", "ingredients", "instruction","https://example.com/image.jpg",
                "12345", true);

        // Simulate preparing the success view
        presenter.prepareSuccessView(outputData);

        // Check if the ViewModel was updated correctly
        CookThisOrReRollState state = viewModel.getState();

        assertEquals("New Recipe", state.getTitle());
        assertEquals("4", state.getServings());
        assertEquals("https://example.com/image.jpg", state.getImage());
        assertEquals("This is a new recipe", state.getSummary());
        assertEquals("30", state.getReadyInMinutes());
        assertEquals("12345", state.getId());
        assertTrue(state.getIsFavourite());
        assertEquals("ingredients", state.getIngredients());
        assertEquals("instruction", state.getInstruction());

    }
}
