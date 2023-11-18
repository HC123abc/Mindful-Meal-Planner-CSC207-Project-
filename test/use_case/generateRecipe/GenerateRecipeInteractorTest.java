package use_case.generateRecipe;


import data_access.InMemoryDataAccess.InMemoryDataAccessUser;
import data_access.InMemoryDataAccess.InMemoryDataAccessUserInterface;
import entity.Preference;
import entity.RandomRecipe;
import entity.RecipeFactory;
import data_access.GenerateRecipe.GenerateRecipeApi;

import entity.User;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

class GenerateRecipeInteractorTest {

    @Test
    void successTest() {
        // Create mock dependencies
        GenerateRecipeDataAccessInterface generateRecipeAPI = new MockGenerateRecipeAPI();
        RecipeFactory recipeFactory = new RecipeFactory();
        InMemoryDataAccessUserInterface mockInMemoryDataAccessUser = new InMemoryDataAccessUser();
        User mockUser = new User("test", "test");
        mockInMemoryDataAccessUser.setActiveUser(mockUser);
        GenerateRecipeOutputBoundary generateRecipePresenter = new GenerateRecipeOutputBoundary() {

            @Override
            public void prepareSuccessView(GenerateRecipeOutputData recipeOutputData) {
                // Get the details of the first recipe from the output data
                String title = recipeOutputData.getTitle();
                String readyInMinutes = recipeOutputData.getReadyInMinutes();
                String servings = recipeOutputData.getServings();
                String summary = recipeOutputData.getSummary();
                String extendedIngredients = recipeOutputData.getExtendedIngredients();
                String extendedInstructions = recipeOutputData.getExtendedInstructions();
                String recipeImageURL = recipeOutputData.getRecipeImageURL();

                // Assert each attribute of the first recipe
                assertEquals("Harley Good Recipe 1", title);
                assertEquals("30", readyInMinutes); // Assuming the first recipe has readyInMinutes as 30
                assertEquals("2", servings); // Assuming the first recipe has servings as 2
                assertEquals("This is a wonderful recipe with fantastic flavors. **Enjoy!**", summary);
                assertTrue(extendedIngredients.contains("Ingredient A"));
                assertTrue(extendedIngredients.contains("Ingredient B"));
                assertTrue(extendedInstructions.contains("Step 1: Do something for recipe 1"));
                assertTrue(recipeImageURL.contains("https://example.com/recipe_1.jpg"));
            }

            @Override
            public void prepareFailView(String error) {
                fail("Use case failure is unexpected.");
            }
        };

        GenerateRecipeInputBoundary interactor = new GenerateRecipeInteractor(
                generateRecipeAPI,
                generateRecipePresenter,
                mockInMemoryDataAccessUser,
                recipeFactory
        );

        // Simulate executing the interactor
        interactor.execute();

    }

    @Test
    void failureTest() {
        // Create mock dependencies or simplified implementations
        GenerateRecipeDataAccessInterface generateRecipeAPI = new GenerateRecipeApi() {
            @Override
            public JSONObject getRecipes(String apiKey, String tags, int count) {
                return null; // Implement the behavior you desire for this method
            }
        };
        InMemoryDataAccessUserInterface mockInMemoryDataAccessUser = new InMemoryDataAccessUser();
        User mockUser = new User("test", "test");
        mockInMemoryDataAccessUser.setActiveUser(mockUser);
        RecipeFactory recipeFactory = new RecipeFactory();
        GenerateRecipeOutputBoundary generateRecipePresenter = new GenerateRecipeOutputBoundary() {

            @Override
            public void prepareSuccessView(GenerateRecipeOutputData recipeOutputData) {
                fail("Use case success is unexpected.");
            }

            @Override
            public void prepareFailView(String error) {
                assertEquals("Error Message: Recipe dont exist: 1. Most likely No more Api tokens (Fix: wait a day) 2. No recipe meet the specified preference (Fix: reduce preferences)", error);
            }
        };
        GenerateRecipeInputBoundary interactor = new GenerateRecipeInteractor(
                generateRecipeAPI,
                generateRecipePresenter,
                mockInMemoryDataAccessUser,
                recipeFactory
        );

        // Simulate executing the interactor
        interactor.execute();

    }
}