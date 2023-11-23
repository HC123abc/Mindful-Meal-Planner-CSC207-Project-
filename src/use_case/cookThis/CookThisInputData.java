package use_case.cookThis;

import java.util.Map;

public class CookThisInputData {
    private String instructions;
    private String ingredients;
    private String previousView;

    public CookThisInputData(String instructions, String ingredients, String previousView) {
        this.instructions = instructions;
        this.ingredients = ingredients;
        this.previousView = previousView;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }
    public String getPreviousView() {
        return previousView;
    }
    public void setPreviousView(String previousView) {
        this.previousView = previousView;
    }
}
