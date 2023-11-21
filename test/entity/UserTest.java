package entity;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UserTest {

    @Test
    public void UserTests(){
        User user = new User("TestUser", "TestPass");
        assertEquals("TestUser", user.getUsername());
        assertEquals(true, user.verifyPassword("TestPass"));
        Preference pref = new Preference();
        user.setPreference(pref);
        FavoriteRecipes fave = new FavoriteRecipes();
        user.setFavoriteRecipes(fave);
        RandomRecipe rand = new RandomRecipe();
        user.setRandomRecipe(rand);
        assertEquals(pref, user.getPreference());
        assertEquals(fave, user.getFavoriteRecipes());
        assertEquals(rand, user.getRandomRecipe());



    }
}
