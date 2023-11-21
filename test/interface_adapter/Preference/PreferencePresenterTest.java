package interface_adapter.Preference;

import interface_adapter.ViewManagerModel;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

public class PreferencePresenterTest {

    @Test
    public void testPrepareSuccessView() {
        // Arrange
        PreferenceViewModel preferenceViewModel = new PreferenceViewModel();
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        PreferencePresenter preferencePresenter = new PreferencePresenter(preferenceViewModel, viewManagerModel);

        // Act
        preferencePresenter.prepareSuccessView("Preferences saved successfully!");

        // Assert
        PreferenceState preferenceState = preferenceViewModel.getState();
        assertEquals("Preferences saved successfully!", preferenceState.getSaved());
    }

}
