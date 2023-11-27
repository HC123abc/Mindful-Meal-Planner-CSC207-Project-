package use_case.unfavouriteThis;

import java.util.Map;

public class UnfavouriteThisOutputData {
    private Map<String, Map<String, String>> recipeOutput;

    public UnfavouriteThisOutputData(Map<String, Map<String, String>> recipeOutput) {
        this.recipeOutput = recipeOutput;
    }

    public Map<String, Map<String, String>> getRecipeOutput() {
        return recipeOutput;
    }
}
