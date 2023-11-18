package entity;

import java.util.ArrayList;

public class FavouriteRecipes {
    private ArrayList<Recipe> favouriteRecipes;

    public FavouriteRecipes() {
        this.favouriteRecipes = new ArrayList<>();
    }

    public ArrayList<Recipe> getFavouriteRecipes() { return favouriteRecipes; }
}
