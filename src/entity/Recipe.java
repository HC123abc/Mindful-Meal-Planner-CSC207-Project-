package entity;

public class Recipe {
    private String title;
    private String id;
    private String readyInMinutes;
    private String servings;
    private String summary;
    private String extendedIngredients;
    private String extendedInstructions;
    private String recipeImageURL;

    public Recipe(String title, String readyInMinutes, String servings, String summary, String extendedIngredients, String extendedInstructions, String recipeImageURL, String id) {
        this.title = title;
        this.readyInMinutes = readyInMinutes;
        this.servings = servings;
        this.summary = summary;
        this.extendedIngredients = extendedIngredients;
        this.extendedInstructions = extendedInstructions;
        this.recipeImageURL = recipeImageURL;
        this.id = id;
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

    public void setId(String id) {
        this.id = id;
    }
}