package use_case.generateRecipe;

import data_access.GenerateRecipe.GenerateRecipeApi;
import entity.*;
import interface_adapter.CookThisOrReRoll.CookThisOrReRollPresenter;
import org.json.JSONArray;
import org.json.JSONObject;

public class GenerateRecipe implements GenerateRecipeInputBoundary {
    //    private GenerateRecipeOutputData generateRecipeOutputData;
    private GenerateRecipeDataAccessInterface generateRecipeAPI;
    private GenerateRecipeOutputBoundary cookThisOrReRollPresenter;
    //    private User user;
    private Preference preference;
    private RandomRecipe randomRecipe;
    private RecipeFactory recipeFactory;


    public GenerateRecipe(GenerateRecipeDataAccessInterface generateRecipeAPI,GenerateRecipeOutputBoundary generateRecipeOutputBoundary, Preference preference, RandomRecipe randomRecipe, RecipeFactory recipeFactory) {
        this.generateRecipeAPI = generateRecipeAPI;
        this.preference = preference;
        this.randomRecipe = randomRecipe;
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
            cookThisOrReRollPresenter.prepareFailView("Recipe Dont exist: 1. No recipe meet the preference 2. No more Api tokens (wait a day)");
        }
        else{
//           do stuff
            JSONArray recipesArray = recipeJSON.getJSONArray("recipes");
            randomRecipe.setRandomRecipeList(recipesArray);
            Recipe recipe = getRecipe();
//          prepare success view
            GenerateRecipeOutputData generateRecipeOutputData = new GenerateRecipeOutputData(recipe.getTitle(),recipe.getReadyInMinutes(), recipe.getServings(), recipe.getSummary(), recipe.getExtendedIngredients(), recipe.getExtendedIngredients(), recipe.getRecipeImageURL());;
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

    public static void main(String[] args) {
        Preference preference = new Preference();
        GenerateRecipeDataAccessInterface generateRecipeAPI = new GenerateRecipeApi();
        RandomRecipe randomRecipe = new RandomRecipe();
        // Create your GenerateRecipeOutputBoundary implementation
        GenerateRecipeOutputBoundary generateRecipeOutputBoundary = new CookThisOrReRollPresenter();
        // Create an instance of GenerateRecipe
        RecipeFactory recipeFactory = new RecipeFactory();
        GenerateRecipe generateRecipe = new GenerateRecipe(generateRecipeAPI,generateRecipeOutputBoundary, preference, randomRecipe, recipeFactory);

        // Execute the recipe generation
        generateRecipe.execute();

    }
}
