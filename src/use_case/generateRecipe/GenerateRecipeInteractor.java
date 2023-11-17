package use_case.generateRecipe;

import data_access.GenerateRecipe.GenerateRecipeApi;
import entity.*;
import interface_adapter.CookThis.CookThisController;
import interface_adapter.CookThis.CookThisPresenter;
import interface_adapter.CookThis.CookThisViewModel;
import interface_adapter.CookThisOrReRoll.CookThisOrReRollPresenter;
import interface_adapter.CookThisOrReRoll.CookThisOrReRollViewModel;
import interface_adapter.ReRoll.ReRollController;
import interface_adapter.ReRoll.ReRollPresenter;
import interface_adapter.ViewManagerModel;
import org.json.JSONArray;
import org.json.JSONObject;
import use_case.cookThis.CookThisInteractor;
import use_case.reRoll.ReRollInputBoundary;
import use_case.reRoll.ReRollInteractor;
import view.CookThisOrReRollView;

public class GenerateRecipeInteractor implements GenerateRecipeInputBoundary {
    //    private GenerateRecipeOutputData generateRecipeOutputData;
    private GenerateRecipeDataAccessInterface generateRecipeAPI;
    private GenerateRecipeOutputBoundary cookThisOrReRollPresenter;
    //    private User user;
    private Preference preference;
    private RandomRecipe randomRecipe;
    private RecipeFactory recipeFactory;


    public GenerateRecipeInteractor(GenerateRecipeDataAccessInterface generateRecipeAPI, GenerateRecipeOutputBoundary generateRecipeOutputBoundary, Preference preference, RandomRecipe randomRecipe, RecipeFactory recipeFactory) {
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
            cookThisOrReRollPresenter.prepareFailView("Error Message: Recipe dont exist: 1. Most likely No more Api tokens (Fix: wait a day) 2. No recipe meet the specified preference (Fix: reduce preferences)" );
        }
        else{
//           do stuff
            JSONArray recipesArray = recipeJSON.getJSONArray("recipes");
            randomRecipe.setRandomRecipeList(recipesArray);
            Recipe recipe = getRecipe();
//          prepare success view
            GenerateRecipeOutputData generateRecipeOutputData = new GenerateRecipeOutputData(recipe.getTitle(),recipe.getReadyInMinutes(), recipe.getServings(), recipe.getSummary(), recipe.getExtendedIngredients(), recipe.getExtendedInstructions(), recipe.getRecipeImageURL());;
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
//        Preference preference = new Preference();
//        GenerateRecipeDataAccessInterface generateRecipeAPI = new GenerateRecipeApi();
//        RandomRecipe randomRecipe = new RandomRecipe();
//        // Create your GenerateRecipeOutputBoundary implementation
//        CookThisOrReRollViewModel cookThisOrReRollViewModel= new CookThisOrReRollViewModel();
//        ViewManagerModel viewManagerModel = new ViewManagerModel();
//        GenerateRecipeOutputBoundary generateRecipeOutputBoundary = new CookThisOrReRollPresenter(cookThisOrReRollViewModel,viewManagerModel);
//        // Create an instance of GenerateRecipe
//        RecipeFactory recipeFactory = new RecipeFactory();
//        GenerateRecipeInteractor generateRecipeInteractor = new GenerateRecipeInteractor(generateRecipeAPI,generateRecipeOutputBoundary, preference, randomRecipe, recipeFactory);
//        ReRollPresenter reRollPresenter = new ReRollPresenter(cookThisOrReRollViewModel,viewManagerModel);
//        ReRollInputBoundary reRollInputBoundary = new ReRollInteractor(randomRecipe,reRollPresenter,recipeFactory);
//        ReRollController reRollController = new ReRollController(reRollInputBoundary);
//        CookThisViewModel cookThisViewModel = new CookThisViewModel();
//        CookThisPresenter cookThisPresenter = new CookThisPresenter(cookThisViewModel,viewManagerModel);
//        CookThisInteractor cookThisInteractor = new CookThisInteractor(cookThisPresenter);
//        CookThisController cookThisController = new CookThisController(cookThisInteractor);
//
//        // Execute the recipe generation
//        generateRecipeInteractor.execute();
//        new CookThisOrReRollView(cookThisOrReRollViewModel,reRollController,  cookThisController, viewManagerModel);

    }
}
