package use_case.unfavouriteThis;

import data_access.InMemoryDataAccess.InMemoryDataAccessUser;
import data_access.InMemoryDataAccess.InMemoryDataAccessUserInterface;
import use_case.FavView.FavViewDataAccessInterface;
import entity.FavouriteRecipes;
import entity.Recipe;
import entity.User;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import use_case.unfavouriteThis.UnfavouriteThisOutputBoundary;
import use_case.unfavouriteThis.UnfavouriteThisOutputData;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class UnfavouriteThisInteractorTest {

    @BeforeAll
    static void setUp() {
    }

    @Test
    void unfavouriteThisInteractorExecuteTest() {

        // create a mock user with a mock recipe in their favourite recipes entity
        User mockUser = new User("user", "password");
        mockUser.setFavouriteRecipes(new FavouriteRecipes());
        FavouriteRecipes mockUserFavRecipes = mockUser.getFavouriteRecipes();
        Recipe mockRecipe = new Recipe("pie recipe", "pi minutes", "pi servings",
                "yummy pi pies", "pi pi pi", "make pi", "pi url", "pi-d");
//        RecipeFactory mockRecipeFactory = new RecipeFactory();
        mockUserFavRecipes.setOneFavouriteRecipe(mockRecipe);

        InMemoryDataAccessUserInterface mockInMemoryDataAccessUser = new InMemoryDataAccessUser();
        mockInMemoryDataAccessUser.setActiveUser(mockUser);
        FavViewDataAccessInterface mockFavViewDataAccessInterface = new FavViewDataAccessInterface() {
            @Override
            public String getRecipeCardUrl(String apiKey, String id) {
                return null;
            }
        };


        UnfavouriteThisOutputBoundary mockFavouriteThisPresenter = new UnfavouriteThisOutputBoundary() {
            @Override
            public void prepareSuccessView(UnfavouriteThisOutputData unfavouriteThisOutputData) {
                Map<String, Map<String, String>> recipeOutput = unfavouriteThisOutputData.getRecipeOutput();
                assertNotNull(unfavouriteThisOutputData);
                assertNotNull(recipeOutput);
                assertEquals(1, recipeOutput.size());
                assertTrue(recipeOutput.containsKey("not pi url"));
            }
        };

        UnfavouriteThisInputData unfavouriteThisInputData = getUnfavouriteThisInputData();

        UnfavouriteThisInteractor unfavouriteThisInteractor = new UnfavouriteThisInteractor(mockFavViewDataAccessInterface,
                                                                mockInMemoryDataAccessUser, mockFavouriteThisPresenter);

        unfavouriteThisInteractor.execute(unfavouriteThisInputData);

    }

    @NotNull
    private static UnfavouriteThisInputData getUnfavouriteThisInputData() {

        // Create a mock recipe entry that is the targeted entry to be removed
        Map<String, Map<String, String>> inputDataRecipeToRemove = new HashMap<>();

        // Create the value of the mock recipe entry to be removed
        Map<String, String> entryValue = new HashMap<>();
        entryValue.put("title", "pie recipe");
        entryValue.put("readyInMinutes", "pi minutes");
        entryValue.put("servings", "pi servings");
        entryValue.put("summary", "yummy pi pies");
        entryValue.put("extendedIngredients", "pi pi pi");
        entryValue.put("extendedInstructions", "make pi");
        entryValue.put("id", "pi-d");

        inputDataRecipeToRemove.put("pi url", entryValue); // this is the Map<String, Map<String, String>> object to be removed

        // Now create a mock of the FavViewState's favourite recipes
        Map<String, Map<String, String>> recipes = new HashMap<>();

        recipes.put("pi url", entryValue); // same key and value as the entry to be removed

        // Create a mock recipe entry that is not the target (ie. not the recipe to be removed)
        Map<String, String> notTargetEntryValue = new HashMap<>();
        notTargetEntryValue.put("title", "not pie recipe");
        notTargetEntryValue.put("readyInMinutes", "not pi minutes");
        notTargetEntryValue.put("servings", "not pi servings");
        notTargetEntryValue.put("summary", "not yummy pi pies");
        notTargetEntryValue.put("extendedIngredients", "not pi pi pi");
        notTargetEntryValue.put("extendedInstructions", "not make pi");
        notTargetEntryValue.put("id", "not pi-d");

        recipes.put("not pi url", notTargetEntryValue); // different key and value from the entry to be removed

        UnfavouriteThisInputData unfavouriteThisInputData = new UnfavouriteThisInputData(recipes, inputDataRecipeToRemove);
        return unfavouriteThisInputData;
    }


}