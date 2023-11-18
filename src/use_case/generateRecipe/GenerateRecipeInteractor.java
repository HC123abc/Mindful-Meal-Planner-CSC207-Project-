package use_case.generateRecipe;

import data_access.InMemoryDataAccess.InMemoryDataAccessUserInterface;
import entity.*;
import org.json.JSONArray;
import org.json.JSONObject;

public class GenerateRecipeInteractor implements GenerateRecipeInputBoundary {
    //    private GenerateRecipeOutputData generateRecipeOutputData;
    private GenerateRecipeDataAccessInterface generateRecipeAPI;
    private GenerateRecipeOutputBoundary cookThisOrReRollPresenter;
    private User user;
    private Preference preference;
    private RandomRecipe randomRecipe;
    private RecipeFactory recipeFactory;


    public GenerateRecipeInteractor(GenerateRecipeDataAccessInterface generateRecipeAPI, GenerateRecipeOutputBoundary generateRecipeOutputBoundary, InMemoryDataAccessUserInterface inMemoryDataAccessUserInterface, RecipeFactory recipeFactory) {
        this.generateRecipeAPI = generateRecipeAPI;
        this.user = inMemoryDataAccessUserInterface.getActiveUser();
        this.preference = user.getPreference();
        this.randomRecipe = user.getRandomRecipe();
        this.recipeFactory = recipeFactory;
        this.cookThisOrReRollPresenter = generateRecipeOutputBoundary;

    }



    @Override
    public void execute() {
//  the input data will be formatted as a string so we can just add it to the query when we call the API call
        String tags = preference.getTags();
        String apiKey = "d6d8b743e3fd4afeac18d54cef0e21ff";
        JSONObject recipeJSON = generateRecipeAPI.getRecipes(apiKey,tags, 20);
        if (recipeJSON == null){
//          prepare fail view
            cookThisOrReRollPresenter.prepareFailView("Error Message: Recipe dont exist: 1. Most likely No more Api tokens (Fix: wait a day) 2. No recipe meet the specified preference (Fix: reduce preferences)" );
        }
        else{
//           do stuff
            JSONArray recipesArray = recipeJSON.getJSONArray("recipes");
            this.randomRecipe.setRandomRecipeList(recipesArray);
            user.setRandomRecipe(this.randomRecipe);
            Recipe recipe = getRecipe();
//          prepare success view
            GenerateRecipeOutputData generateRecipeOutputData = new GenerateRecipeOutputData(recipe.getTitle(),recipe.getReadyInMinutes(), recipe.getServings(), recipe.getSummary(), recipe.getExtendedIngredients(), recipe.getExtendedInstructions(), recipe.getRecipeImageURL(), recipe.getId());;
            cookThisOrReRollPresenter.prepareSuccessView(generateRecipeOutputData);

        }
    }

    private Recipe getRecipe() {
        int current_i = randomRecipe.getCurrentRecipeIndex();
        System.out.println((current_i));
        if(current_i == randomRecipe.getRandomRecipeList().length()-1){
            randomRecipe.setCurrentRecipeIndex(0);
        }
        else {
            randomRecipe.setCurrentRecipeIndex(current_i + 1);
        }
        Recipe recipe = recipeFactory.create(randomRecipe.getRandomRecipeList().getJSONObject(current_i));

        return recipe;
    }

}
