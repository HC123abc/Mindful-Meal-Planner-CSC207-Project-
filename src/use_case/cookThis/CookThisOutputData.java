package use_case.cookThis;

import java.util.Map;

public class CookThisOutputData {
    private String instruction;
    private Map<String, String> ingredientsWithImage;

    public CookThisOutputData(Map<String, String> ingredientsWithImage, String instruction) {
        this.ingredientsWithImage = ingredientsWithImage;
        this.instruction = instruction;
    }

    public String getInstruction() {
        return instruction;
    }

    public Map<String, String> getIngredientsWithImage() {
        return ingredientsWithImage;
    }

}
