package use_case.cookThis;

import java.util.Map;

public class CookThisOutputData {
    private String instruction;
    private Map<String, String> ingredientsWithImage;
    private String previousView;

    public CookThisOutputData(Map<String, String> ingredientsWithImage, String instruction, String previousView) {
        this.ingredientsWithImage = ingredientsWithImage;
        this.instruction = instruction;
        this.previousView = previousView;
    }

    public String getInstruction() {
        return instruction;
    }

    public Map<String, String> getIngredientsWithImage() {
        return ingredientsWithImage;
    }

    public String getPreviousView() {
        return previousView;
    }
}
