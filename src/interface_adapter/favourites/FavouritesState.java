package interface_adapter.favourites;

import java.net.URL;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

public class FavouritesState {
    private Map<Map<String,String>, String> favouriteRecipes = new HashMap<>();

    //Constructors
    public FavouritesState() {
        // empty for now
    }

    // Getters
    public Map<Map<String,String>, String> getFavouriteRecipes() {
        return favouriteRecipes;
    }

    // Setters
    public void setFavouriteRecipes(Map<Map<String,String>, String> recipes) { this.favouriteRecipes = recipes; }
    public void removeFavourite(Map<String,String> recipe) {
        favouriteRecipes.remove(recipe);
    }

    public void clearAllFavourites() { favouriteRecipes = new HashMap<>(); } // extra use case that can be implemented as extra


}
