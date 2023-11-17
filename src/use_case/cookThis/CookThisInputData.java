package use_case.cookThis;

import java.util.Map;

public class CookThisInputData {
    private String instructions;
    private String ingredients;

    public CookThisInputData(String instructions, String ingredients) {
        this.instructions = instructions;
        this.ingredients = ingredients;
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
}
