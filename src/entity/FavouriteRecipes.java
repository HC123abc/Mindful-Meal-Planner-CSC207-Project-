package entity;

import java.util.ArrayList;

public class FavouriteRecipes {
    private ArrayList<Recipe> favouriteRecipes;

    public FavouriteRecipes() {
        this.favouriteRecipes = new ArrayList<>();
    }

    public ArrayList<Recipe> getFavouriteRecipes() {
        return favouriteRecipes;
    }

    public void setOneFavouriteRecipe(Recipe recipe) {
        favouriteRecipes.add(recipe);
    }

    public void replaceRecipes(ArrayList<Recipe> recipes){ this.favouriteRecipes = recipes; }

    public void removeOneRecipe(Recipe recipe) {
        for (int i = 0; i < favouriteRecipes.size(); i++) {
            Recipe recipe1 = favouriteRecipes.get(i);
            if (recipe1.getId().equals(recipe.getId())) {
                favouriteRecipes.remove(i);
                System.out.println("Recipe removed successfully.");
                return;
            }
        }
    }
}
