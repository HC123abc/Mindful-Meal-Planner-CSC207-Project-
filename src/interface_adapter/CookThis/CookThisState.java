package interface_adapter.CookThis;

import java.util.HashMap;
import java.util.Map;

public class CookThisState {
    private Map<String, String> ingredientsWithImage = new HashMap<>();
    private String instruction = "";

    // Constructors
    public CookThisState(CookThisState copy) {
        this.ingredientsWithImage = copy.ingredientsWithImage;
        this.instruction = copy.instruction;

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
}


