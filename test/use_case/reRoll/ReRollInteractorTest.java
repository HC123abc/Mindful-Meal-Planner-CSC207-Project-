package use_case.reRoll;

import data_access.InMemoryDataAccess.InMemoryDataAccessUser;
import data_access.InMemoryDataAccess.InMemoryDataAccessUserInterface;
import entity.RandomRecipe;
import entity.RecipeFactory;
import entity.User;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ReRollInteractorTest {

    @Test
    void executeReturnsNextRecipe() {
        // Mock dependencies
        User mockUser = new User("test", "test");
        InMemoryDataAccessUserInterface mockInMemoryDataAccessUser = new InMemoryDataAccessUser();
        mockInMemoryDataAccessUser.setActiveUser(mockUser);
        RandomRecipe mockRandomRecipe = new RandomRecipe();
        RecipeFactory mockRecipeFactory = new RecipeFactory();
        ReRollOutputBoundary mockReRollPresenter = new ReRollOutputBoundary() {
            @Override
            public void prepareSuccessView(ReRollOutputData reRollOutputData) {
                assertNotNull(reRollOutputData);
                // Add assertions to validate the returned recipe data
                assertNotNull(reRollOutputData.getTitle());
                assertNotNull(reRollOutputData.getReadyInMinutes());
                assertNotNull(reRollOutputData.getServings());
                assertNotNull(reRollOutputData.getSummary());
                assertNotNull(reRollOutputData.getExtendedIngredients());
                assertNotNull(reRollOutputData.getExtendedInstructions());
                assertNotNull(reRollOutputData.getRecipeImageURL());
            }
        };

        JSONArray mockedRecipes = new JSONArray();

        for (int i = 0; i < 5; i++) {
            JSONArray ingredientsArray = new JSONArray();
            JSONObject ingredient = new JSONObject();
            ingredient.put("name", "Ingredient " + (char) ('A' + i)); // Generate different ingredients for each recipe
            ingredient.put("amount", 100.0);
            ingredient.put("unit", "g");
            ingredientsArray.put(ingredient);

            JSONArray instructionsArray = new JSONArray();
            JSONObject instruction = new JSONObject();
            instruction.put("number", 1);
            instruction.put("step", "Do something");
            instructionsArray.put(instruction);

            JSONObject recipeObject = new JSONObject();
            recipeObject.put("title", "Recipe " + (i + 1));
            recipeObject.put("readyInMinutes", 20 + i * 5);
            recipeObject.put("servings", 2 + i);
            recipeObject.put("summary", "Recipe summary " + (i + 1));
            recipeObject.put("extendedIngredients", ingredientsArray);
            recipeObject.put("id", i);
            JSONObject analyzedInstructions = new JSONObject();
            JSONArray analyzedInstructionsArray = new JSONArray();
            analyzedInstructionsArray.put(analyzedInstructions);
            analyzedInstructions.put("steps", instructionsArray);
            recipeObject.put("analyzedInstructions", analyzedInstructionsArray);
            recipeObject.put("image", "https://example.com/recipe" + (i + 1) + ".jpg");

            mockedRecipes.put(recipeObject);
        }
        mockRandomRecipe.setRandomRecipeList(mockedRecipes);
        mockUser.setRandomRecipe(mockRandomRecipe);

        ReRollInteractor interactor = new ReRollInteractor(mockInMemoryDataAccessUser, mockReRollPresenter, mockRecipeFactory);
        interactor.execute();
    }

    @Test
    void executeUpdatesMultiple() {
        // Mock dependencies
        InMemoryDataAccessUserInterface mockInMemoryDataAccessUser = new InMemoryDataAccessUser();
        User mockUser = new User("test", "test");
        mockInMemoryDataAccessUser.setActiveUser(mockUser);
        RandomRecipe mockRandomRecipe = new RandomRecipe();
        RecipeFactory mockRecipeFactory = new RecipeFactory();


        // Create a JSONArray of mocked recipes (JSONObjects)

        JSONArray mockedRecipes = new JSONArray();

        for (int i = 0; i < 5; i++) {
            JSONArray ingredientsArray = new JSONArray();
            JSONObject ingredient = new JSONObject();
            ingredient.put("name", "Ingredient " + (char) ('A' + i)); // Generate different ingredients for each recipe
            ingredient.put("amount", 100.0);
            ingredient.put("unit", "g");
            ingredientsArray.put(ingredient);

            JSONArray instructionsArray = new JSONArray();
            JSONObject instruction = new JSONObject();
            instruction.put("number", 1);
            instruction.put("step", "Do something");
            instructionsArray.put(instruction);

            JSONObject recipeObject = new JSONObject();
            recipeObject.put("title", "Recipe " + (i + 1));
            recipeObject.put("readyInMinutes", 20 + i * 5);
            recipeObject.put("servings", 2 + i);
            recipeObject.put("summary", "Recipe summary " + (i + 1));
            recipeObject.put("extendedIngredients", ingredientsArray);
            JSONObject analyzedInstructions = new JSONObject();
            JSONArray analyzedInstructionsArray = new JSONArray();
            analyzedInstructionsArray.put(analyzedInstructions);
            analyzedInstructions.put("steps", instructionsArray);
            recipeObject.put("analyzedInstructions", analyzedInstructionsArray);
            recipeObject.put("image", "https://example.com/recipe" + (i + 1) + ".jpg");
            recipeObject.put("id", (i + 1));

            mockedRecipes.put(recipeObject);
        }
        mockRandomRecipe.setRandomRecipeList(mockedRecipes);

        // Set the mocked recipes in activeUser
        mockUser.setRandomRecipe(mockRandomRecipe);
        ReRollOutputBoundary mockReRollPresenter = new ReRollOutputBoundary() {
            @Override
            public void prepareSuccessView(ReRollOutputData reRollOutputData) {
                JSONArray mockedRecipes = new JSONArray();

                for (int i = 0; i < 5; i++) {
                    String recipe = String.format(
                            "{\"title\":\"Recipe %d\",\"readyInMinutes\":%d,\"servings\":%d,\"id\":%d,\"summary\":\"Recipe summary %d\",\"extendedIngredients\":\"Ingredient %c\",\"extendedInstructions\":\"Step 1: Do something\",\"recipeImageURL\":\"https://example.com/recipe%d.jpg\"}",
                            i + 1, 20 + i * 5, 2 + i,i, i + 1, 'A' + i, i + 1
                    );
                    mockedRecipes.put(new JSONObject(recipe));
                }

                assertNotNull(reRollOutputData);
                int currentIndex = 0;
                if (mockRandomRecipe.getCurrentRecipeIndex() == 0) {
                    currentIndex = 4;
                }
                else{
                    currentIndex = mockRandomRecipe.getCurrentRecipeIndex() - 1;
                }
                JSONObject expectedRecipe = mockedRecipes.getJSONObject(currentIndex);

                assertEquals(expectedRecipe.getString("title"), reRollOutputData.getTitle());
                assertEquals(expectedRecipe.getInt("readyInMinutes"), Integer.parseInt(reRollOutputData.getReadyInMinutes()));
                assertEquals(expectedRecipe.getInt("servings"), Integer.parseInt(reRollOutputData.getServings()));
                assertEquals(expectedRecipe.getString("summary"), reRollOutputData.getSummary());

                // Validate output data attributes if needed
            }
        };
        ReRollInteractor interactor = new ReRollInteractor(mockInMemoryDataAccessUser, mockReRollPresenter, mockRecipeFactory);

        // Execute multiple times and check if the output data and current index of RandomRecipe are consistent
        for (int i = 0; i < 10; i++) {
            interactor.execute();
        }
    }
}