package interface_adapter.FavView;

import java.util.HashMap;
import java.util.Map;

public class FavViewState {
    private Map<String, Map<String, String>> recipeWithCardImage = new HashMap<>();
    private String error = null;

    // Constructors
    public FavViewState(FavViewState copy) {
        this.recipeWithCardImage = copy.recipeWithCardImage;
    }

    public FavViewState() {
        // Default constructor
    }

    // Getters and Setters
    // Getters
    public Map<String, Map<String, String>>  getRecipeWithCardImage() {
        return recipeWithCardImage;
    }

    // Setters
    public void setRecipeWithCardImage(Map<String, Map<String, String>> recipeWithCardImage) {
        this.recipeWithCardImage = recipeWithCardImage;
    }

    public void setRecipeError(String error) {
        this.error = error;
    }

    public String getError() {
        return error;
    }
}



