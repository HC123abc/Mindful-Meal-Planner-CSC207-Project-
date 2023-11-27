package use_case.FavView;

import data_access.InMemoryDataAccess.InMemoryDataAccessUserInterface;
import entity.Recipe;
import entity.User;
import entity.FavouriteRecipes;

import org.junit.internal.runners.statements.Fail;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class FavViewInteractorTest {

    @Test
    void testExecute_Success() {
        // Mocking the dependencies
        FavViewDataAccessInterface generateRecipeCardAPI = new FavViewDataAccessInterface() {
            @Override
            public String getRecipeCardUrl(String apiKey, String id) {
                return "https://example.com/recipe-card";
            }
        };

        InMemoryDataAccessUserInterface inMemoryDataAccessUser = new InMemoryDataAccessUserInterface() {
            @Override
            public User getActiveUser() {
                User user = new User("testUser", "testPassword");
                FavouriteRecipes favouriteRecipes = new FavouriteRecipes();
                Recipe recipe1 = new Recipe("Recipe 1", "30", "4", "Summary 1", "Ingredients 1", "Instructions 1", "https://example.com/image1.jpg", "1");
                Recipe recipe2 = new Recipe("Recipe 2", "25", "3", "Summary 2", "Ingredients 2", "Instructions 2", "https://example.com/image2.jpg", "2");
                favouriteRecipes.setOneFavouriteRecipe(recipe1);
                favouriteRecipes.setOneFavouriteRecipe(recipe2);
                user.setFavouriteRecipes(favouriteRecipes);
                return user;
            }
            //not used
            @Override
            public void setActiveUser(User user){
            }
        };

        FavViewOutputBoundary favViewPresenter = new FavViewOutputBoundary() {

            @Override
            public void prepareSuccessView(FavViewOutputData recipeOutputData) {
                Map<String, Map<String, String>> expectedOutputMap = new HashMap<>();
                Map<String, String> recipeDetails1 = new HashMap<>();
                recipeDetails1.put("title", "Recipe 1");
                recipeDetails1.put("readyInMinutes", "30");
                recipeDetails1.put("servings", "4");
                recipeDetails1.put("summary", "Summary 1");
                recipeDetails1.put("extendedIngredients", "Ingredients 1");
                recipeDetails1.put("extendedInstructions", "Instructions 1");
                recipeDetails1.put("recipeImageURL", "https://example.com/image1.jpg");
                recipeDetails1.put("id", "1");
                recipeDetails1.put("isFavourite", "true");

                Map<String, String> recipeDetails2 = new HashMap<>();
                recipeDetails2.put("title", "Recipe 2");
                recipeDetails2.put("readyInMinutes", "25");
                recipeDetails2.put("servings", "3");
                recipeDetails2.put("summary", "Summary 2");
                recipeDetails2.put("extendedIngredients", "Ingredients 2");
                recipeDetails2.put("extendedInstructions", "Instructions 2");
                recipeDetails2.put("recipeImageURL", "https://example.com/image2.jpg");
                recipeDetails2.put("id", "2");
                recipeDetails2.put("isFavourite", "true");

                expectedOutputMap.put("https://example.com/recipe-card", recipeDetails1);
                expectedOutputMap.put("https://example.com/recipe-card", recipeDetails2);
                assertEquals(expectedOutputMap, recipeOutputData.getRecipeWithCardImage());
            }

            @Override
            public void prepareFailView(String error) {
                fail("Unexpected");
            }
        };

        // Setting up the expected output
        Map<String, Map<String, String>> expectedOutputMap = new HashMap<>();
        Map<String, String> recipeDetails1 = new HashMap<>();
        recipeDetails1.put("title", "Recipe 1");
        recipeDetails1.put("readyInMinutes", "30");
        recipeDetails1.put("servings", "4");
        recipeDetails1.put("summary", "Summary 1");
        recipeDetails1.put("extendedIngredients", "Ingredients 1");
        recipeDetails1.put("extendedInstructions", "Instructions 1");
        recipeDetails1.put("recipeImageURL", "https://example.com/image1.jpg");
        recipeDetails1.put("id", "1");
        recipeDetails1.put("isFavourite", "true");

        Map<String, String> recipeDetails2 = new HashMap<>();
        recipeDetails2.put("title", "Recipe 2");
        recipeDetails2.put("readyInMinutes", "25");
        recipeDetails2.put("servings", "3");
        recipeDetails2.put("summary", "Summary 2");
        recipeDetails2.put("extendedIngredients", "Ingredients 2");
        recipeDetails2.put("extendedInstructions", "Instructions 2");
        recipeDetails2.put("recipeImageURL", "https://example.com/image2.jpg");
        recipeDetails2.put("id", "2");
        recipeDetails2.put("isFavourite", "true");

        expectedOutputMap.put("https://example.com/recipe-card", recipeDetails1);
        expectedOutputMap.put("https://example.com/recipe-card", recipeDetails2);

        // Setting the expected output

        // Creating and executing the interactor
        FavViewInteractor interactor = new FavViewInteractor(generateRecipeCardAPI, favViewPresenter, inMemoryDataAccessUser);
        interactor.execute();
    }
    @Test
    void testExecute_Failure() {
        // Mocking the dependencies
        FavViewDataAccessInterface generateRecipeCardAPI = new FavViewDataAccessInterface() {
            @Override
            public String getRecipeCardUrl(String apiKey, String id) {
                return "https://example.com/recipe-card";
            }
        };

        InMemoryDataAccessUserInterface inMemoryDataAccessUser = new InMemoryDataAccessUserInterface() {
            @Override
            public User getActiveUser() {
                User user = new User("testUser", "testPassword");
                FavouriteRecipes favouriteRecipes = new FavouriteRecipes();

                return user;
            }
            //not used
            @Override
            public void setActiveUser(User user){
            }
        };

        FavViewOutputBoundary favViewPresenter = new FavViewOutputBoundary() {

            @Override
            public void prepareSuccessView(FavViewOutputData recipeOutputData) {
                fail("Unexpected");
            }

            @Override
            public void prepareFailView(String error) {
                assertEquals("You have no favourite recipes.  Add some!", error);
            }
        };


        // Creating and executing the interactor
        FavViewInteractor interactor = new FavViewInteractor(generateRecipeCardAPI, favViewPresenter, inMemoryDataAccessUser);
        interactor.execute();
    }
}
