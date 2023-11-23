package use_case.generateRecipe;

public class GenerateRecipeOutputData {
    private String title;
    private String readyInMinutes;
    private String servings;
    private String summary;
    private String extendedIngredients;
    private String extendedInstructions;
    private String recipeImageURL;
    private String id;
    private boolean isFavourite;

    public GenerateRecipeOutputData(String title, String readyInMinutes, String servings, String summary, String extendedIngredients, String extendedInstructions, String recipeImageURL, String id, boolean isFavourite) {
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

    public String getReadyInMinutes() {
        return readyInMinutes;
    }

    public String getServings() {
        return servings;
    }

    public String getSummary() {
        return summary;
    }

    public String getExtendedIngredients() {
        return extendedIngredients;
    }

    public String getExtendedInstructions() {
        return extendedInstructions;
    }

    public String getRecipeImageURL() {
        return recipeImageURL;
    }

    public String getId() {
        return id;
    }


    public boolean getIsFavourite() {
        return isFavourite;
    }

}
