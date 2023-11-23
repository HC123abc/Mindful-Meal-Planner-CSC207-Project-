package use_case.FavView;

import java.util.Map;

public class FavViewOutputData {
    private Map<String, Map<String, String>> recipeWithCardImage;
    public FavViewOutputData(Map<String, Map<String, String>> recipeWithCardImage) {
        this.recipeWithCardImage = recipeWithCardImage;
    }
    //getters
    public Map<String, Map<String, String>> getRecipeWithCardImage() {
        return recipeWithCardImage;
    }


}
