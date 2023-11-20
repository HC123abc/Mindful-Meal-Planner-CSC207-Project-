package use_case.cookThis;

import java.util.HashMap;
import java.util.Map;

public class CookThisInteractor implements CookThisInputBoundary{
    private CookThisOutputBoundary cookThisPresenter;
    public CookThisInteractor(CookThisOutputBoundary cookThisPresenter){
        this.cookThisPresenter = cookThisPresenter;
    }
    @Override
    public void execute(CookThisInputData input) {
        String extendedIngredients = input.getIngredients();
        String extendedInstructions = input.getInstructions();
        String previousView = input.getPreviousView();
        // Initialize the Map to hold ingredient names and their image URLs
        Map<String, String> ingredientsWithImage = new HashMap<>();

// Splitting the extended ingredients and mapping them
        String[] ingredientsArray = extendedIngredients.split("\n");

        for (String ingredient : ingredientsArray) {
            // Split the ingredient string to separate the image URL and the ingredient details
            String[] parts = ingredient.split(" ", 2);

            if (parts.length == 2) {
                String ingredientDetails = parts[1];
                String ingredientImageURL = parts[0];

                // Map the ingredient details to its image URL
                ingredientsWithImage.put(ingredientDetails, ingredientImageURL);
            }
        }
        CookThisOutputData cookThisOutputData = new CookThisOutputData(ingredientsWithImage,extendedInstructions,previousView);
        cookThisPresenter.prepareSuccessView(cookThisOutputData);

    }
}
