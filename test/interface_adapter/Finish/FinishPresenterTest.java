package interface_adapter.Finish;

import interface_adapter.ViewManagerModel;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FinishPresenterTest {

    @Test
    void prepareSuccessViewTest() {
        // Mock ViewModel and ViewManagerModel
        FinishViewModel mockViewModel = new FinishViewModel();
        ViewManagerModel mockViewManagerModel = new ViewManagerModel();

        // Creating FinishPresenter instance
        FinishPresenter presenter = new FinishPresenter(mockViewManagerModel,mockViewModel);
        presenter.prepareSuccessView();
        // Verifying if viewManagerModel.setActiveView is equal to "Finish"
        assertEquals("MainPage", mockViewManagerModel.getActiveView());

    }

}
