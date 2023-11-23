package use_case.favouriteThis;

public class favouriteThisInputData {
    private String title;
    private String summary;
    private String recipeImageURL;
    private String servings;
    private String readyInMinutes;
    private String extendedIngredients;
    private String extendedInstructions;
    private String id;
    private boolean isFavourite;

    public favouriteThisInputData(String title, String readyInMinutes, String servings, String summary, String extendedIngredients, String extendedInstructions, String recipeImageURL, String id, boolean isFavourite) {
        this.title = title;
        this.readyInMinutes = readyInMinutes;
        this.servings = servings;
        this.summary = summary;
        this.extendedIngredients = extendedIngredients;
        this.extendedInstructions = extendedInstructions;
        this.recipeImageURL = recipeImageURL;
        this.id = id;
        this.isFavourite = isFavourite;
    }

    public String getTitle() {
        return title;
    }

    public String getSummary() { return summary; }

    public String getRecipeImageURL() { return recipeImageURL; }

    public String getServings() {
        return servings;
    }

    public String getReadyInMinutes() {
        return readyInMinutes;
    }

    public String getExtendedIngredients() {
        return extendedIngredients;
    }

    public String getExtendedInstructions() {
        return extendedInstructions;
    }

    public String getId() {
        return id;
    }

    public boolean getIsFavourite() { return isFavourite; }
}
