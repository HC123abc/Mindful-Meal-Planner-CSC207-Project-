package use_case.favourites;

import java.util.Map;

public class FavouritesOutputData {

    // Property
    private Map<Map<String, String>, String> favouriteRecipes;

    // Constructor
    public FavouritesOutputData(Map<Map<String, String>, String> recipes) {
        this.favouriteRecipes = recipes;

    }

    // Getter
    public Map<Map<String, String>, String> getAllRecipes() {
        return this.favouriteRecipes;
    }
}
