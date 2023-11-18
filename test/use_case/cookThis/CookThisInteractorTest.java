package use_case.cookThis;


import org.junit.jupiter.api.Test;


import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class CookThisInteractorTest {

    @Test
    void successTest() {
        // Mock presenter
        CookThisOutputBoundary mockPresenter = new CookThisOutputBoundary() {
            @Override
            public void prepareSuccessView(CookThisOutputData cookThisOutputData) {
                Map<String, String> ingredientsWithImage = cookThisOutputData.getIngredientsWithImage();
                String extendedInstructions = cookThisOutputData.getInstruction();

                // Check if the ingredientsWithImage map is not empty
                assertTrue(!ingredientsWithImage.isEmpty());

                // Check if the extendedInstructions is not null or empty
                assertNotNull(extendedInstructions);
                assertEquals("Step 1. Do something",extendedInstructions);

            }
        };

        // Create input data
        CookThisInputData inputData = new CookThisInputData("Step 1. Do something","Ingredient A imageA.jpg: 100 g");

        // Create the interactor with the mock presenter
        CookThisInteractor interactor = new CookThisInteractor(mockPresenter);

        // Execute the interactor
        interactor.execute(inputData);
    }

}
