package use_case.favouriteThis;

import data_access.InMemoryDataAccess.InMemoryDataAccessUser;
import data_access.InMemoryDataAccess.InMemoryDataAccessUserInterface;
import entity.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class favouriteThisInteractorTest {

    @BeforeEach
    void setUp() {
    }

    @Test
    void favouriteThisInteractorExecuteTest() {
        // create a mock user with a mock recipe in their favourite recipes entity
        User mockUser = new User("user", "password");
        mockUser.setFavouriteRecipes(new FavouriteRecipes());
        FavouriteRecipes mockUserFavRecipes = mockUser.getFavouriteRecipes();
        InMemoryDataAccessUserInterface mockInMemoryDataAccessUser = new InMemoryDataAccessUser();
        mockInMemoryDataAccessUser.setActiveUser(mockUser);
        Recipe mockRecipe = new Recipe( "pie recipe",
                                        "pi minutes",
                                        "pi servings",
                                        "yummy pi pies",
                                        "pi pi pi",
                                        "make pi",
                                        "pi url",
                                        "pi-d");
//        RecipeFactory mockRecipeFactory = new RecipeFactory();
        mockUserFavRecipes.setOneFavouriteRecipe(mockRecipe);


        favouriteThisOutputBoundary mockFavouriteThisPresenter = new favouriteThisOutputBoundary() {
            @Override
            public void prepareSuccessView(favouriteThisOutputData fTOutputData) {
                boolean outputDataIsFavouriteProperty = fTOutputData.getIsFavourite();
                assertEquals(true, outputDataIsFavouriteProperty);

                FavouriteRecipes mockUserFavRecipesResult = mockUser.getFavouriteRecipes();
                assertNotNull(mockUserFavRecipesResult);
                assertTrue(mockUserFavRecipesResult.getFavouriteRecipes().contains(mockRecipe));
            }
        };

        favouriteThisInputData favouriteThisInputData = new favouriteThisInputData( "pie recipe",
                                                                                    "pi minutes",
                                                                                    "pi servings",
                                                                                    "yummy pi pies",
                                                                                    "pi pi pi",
                                                                                    "make pi",
                                                                                    "pi url",
                                                                                    "pi-d",
                                                                                    false);

        favouriteThisInteractor favouriteThisInteractor = new favouriteThisInteractor(  mockInMemoryDataAccessUser,
                                                                                        mockFavouriteThisPresenter);

        favouriteThisInteractor.execute(favouriteThisInputData);

    }
}