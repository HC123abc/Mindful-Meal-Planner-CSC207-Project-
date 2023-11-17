package interface_adapter.CookThisOrReRoll;

public class CookThisOrReRollState {
    private String recipeError = null;
    private String title = "";
    private String summary = "";
    private String image = "";
    private String servings = "";
    private String readyInMinutes = "";
    private String ingredients = "";
    private String instruction = "";

    // Constructors
    public CookThisOrReRollState(CookThisOrReRollState copy) {
        this.title = copy.title;
        this.summary = copy.summary;
        this.image = copy.image;
        this.servings = copy.servings;
        this.readyInMinutes = copy.readyInMinutes;
        this.ingredients = copy.ingredients;
        this.instruction = copy.instruction;

    }

    public CookThisOrReRollState() {
        // Default constructor
    }

    // Getters and Setters
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getServings() {
        return servings;
    }

    public void setServings(String servings) {
        this.servings = servings;
    }
    public String getReadyInMinutes() {
        return readyInMinutes;
    }

    public void setReadyInMinutes(String readyInMinutes) {
        this.readyInMinutes = readyInMinutes;
    }
    public String getRecipeError() {
        return recipeError;
    }

    public void setRecipeError(String recipeError) {
        this.recipeError = recipeError;
    }

    public String getIngredients() {
        return ingredients;
    }

    public String getInstruction() {
        return instruction;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public void setInstruction(String instruction) {
        this.instruction = instruction;
    }
}
