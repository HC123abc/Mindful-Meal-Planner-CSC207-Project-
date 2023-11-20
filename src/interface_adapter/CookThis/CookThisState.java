package interface_adapter.CookThis;

import java.util.HashMap;
import java.util.Map;

public class CookThisState {
    private Map<String, String> ingredientsWithImage = new HashMap<>();
    private String instruction = "";
    private String previousView = "";

    // Constructors
    public CookThisState(CookThisState copy) {
        this.ingredientsWithImage = copy.ingredientsWithImage;
        this.instruction = copy.instruction;
        this.previousView = copy.previousView;

    }

    public CookThisState() {
        // Default constructor
    }

    // Getters and Setters
    public Map<String, String> getIngredientsWithImage() {
        return ingredientsWithImage;
    }

    public void setInstruction(String instruction) {
        this.instruction = instruction;
    }

    public void setIngredientsWithImage(Map<String, String> ingredientsWithImage) {
        this.ingredientsWithImage = ingredientsWithImage;
    }
    public String getInstruction(){
        return instruction;
    }
    public String getPreviousView() {
        return previousView;
    }

    public void setPreviousView(String previousView) {
        this.previousView = previousView;
    }
}


