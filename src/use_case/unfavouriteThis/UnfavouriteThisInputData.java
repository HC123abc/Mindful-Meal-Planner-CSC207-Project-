package use_case.unfavouriteThis;

public class UnfavouriteThisInputData {
    private String title;
    private String summary;
    private String recipeImageURL;
    private String servings;
    private String readyInMinutes;
    private String extendedIngredients;
    private String extendedInstructions;
    private String id;

    public UnfavouriteThisInputData(String title, String summary, String recipeImageURL, String servings,
                                    String readyInMinutes, String extendedIngredients,
                                    String extendedInstructions, String id) {

        this.title = title;
        this.summary = summary;
        this.recipeImageURL = recipeImageURL;
        this.servings = servings;
        this.readyInMinutes = readyInMinutes;
        this.extendedIngredients = extendedIngredients;
        this.extendedInstructions = extendedInstructions;
        this.id = id;
    }


    public String getTitle() {
        return title;
    }

    public String getSummary() {
        return summary;
    }

    public String getRecipeImageURL() {
        return recipeImageURL;
    }

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


}
