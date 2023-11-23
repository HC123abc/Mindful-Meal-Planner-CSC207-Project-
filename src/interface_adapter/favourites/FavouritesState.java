package interface_adapter.favourites;

import java.net.URL;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

public class FavouritesState {
    private Map<Map<String,String>, URL> favouriteRecipes;

    //Constructors
    public FavouritesState() {
        //TODO IMPLEMENT THIS CONSTRUCTOR
    }

    // Getters
    public Map<Map<String,String>, URL> getFavouriteRecipes() {
        return favouriteRecipes;
    }

    // Setters
    public void removeFavourite(Map<String,String> recipe) {
        favouriteRecipes.remove(recipe);
    }


}
