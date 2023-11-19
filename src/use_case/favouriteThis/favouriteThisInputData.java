package use_case.favouriteThis;

public class favouriteThisInputData {
    private String recipeError;  // TODO add implementation for error handling
    private String title;
    private String summary;
    private String image;
    private String servings;
    private String readyInMinutes;
    private String ingredients;
    private String instruction;
    private String id;
    private boolean isFavourite;

    public favouriteThisInputData(String title, String summary, String image,
                                  String servings, String readyInMinutes,
                                  String ingredients, String instruction,
                                  String id, boolean isFavourite) {

    }

    public String getTitle() {
        return title;
    }

    public String getSummary() { return summary; }

    public String getImage() { return image; }

    public String getServings() {
        return servings;
    }

    public String getReadyInMinutes() {
        return readyInMinutes;
    }

    public String getIngredients() {
        return ingredients;
    }

    public String getInstructions() {
        return instruction;
    }

    public String getId() {
        return id;
    }

    public boolean getIsFavourite() { return isFavourite; }
}
