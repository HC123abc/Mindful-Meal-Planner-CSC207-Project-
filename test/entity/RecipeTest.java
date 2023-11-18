package entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class RecipeTest {

    private Recipe recipe;

    @BeforeEach
    void init() {
        recipe = new Recipe(
                "Spaghetti Carbonara",
                "20",
                "4",
                "A classic Italian pasta dish...",
                "Spaghetti, Eggs, Bacon, Parmesan Cheese",
                "1. Boil spaghetti... 2. Fry bacon...",
                "https://example.com/spaghetti-carbonara.jpg","231"
        );
    }

    @Test
    void getTitleTest() {
        assertEquals("Spaghetti Carbonara", recipe.getTitle());
    }

    @Test
    void getReadyInMinutesTest() {
        assertEquals("20", recipe.getReadyInMinutes());
    }

    @Test
    void getServingsTest() {
        assertEquals("4", recipe.getServings());
    }

    @Test
    void getSummaryTest() {
        assertEquals("A classic Italian pasta dish...", recipe.getSummary());
    }

    @Test
    void getExtendedIngredientsTest() {
        assertEquals("Spaghetti, Eggs, Bacon, Parmesan Cheese", recipe.getExtendedIngredients());
    }

    @Test
    void getExtendedInstructionsTest() {
        assertEquals("1. Boil spaghetti... 2. Fry bacon...", recipe.getExtendedInstructions());
    }

    @Test
    void getRecipeImageURLTest() {
        assertEquals("https://example.com/spaghetti-carbonara.jpg", recipe.getRecipeImageURL());
    }
    @Test
    void getId() {
        assertEquals("231", recipe.getId());
    }
}
