package use_case.generateRecipe;

import data_access.InMemoryDataAccess.InMemoryDataAccessUserInterface;
import entity.*;
import org.json.JSONArray;
import org.json.JSONObject;

public class GenerateRecipeInteractor implements GenerateRecipeInputBoundary {
    //    private GenerateRecipeOutputData generateRecipeOutputData;
    private GenerateRecipeDataAccessInterface generateRecipeAPI;
    private GenerateRecipeOutputBoundary cookThisOrReRollPresenter;
    private InMemoryDataAccessUserInterface inMemoryDataAccessUser;
    private User user;
    private Preference preference;
    private RandomRecipe randomRecipe;
    private SimpleRecipeFactoryInterface recipeFactory;


    public GenerateRecipeInteractor(GenerateRecipeDataAccessInterface generateRecipeAPI, GenerateRecipeOutputBoundary generateRecipeOutputBoundary, InMemoryDataAccessUserInterface inMemoryDataAccessUser, SimpleRecipeFactoryInterface recipeFactory) {
        this.generateRecipeAPI = generateRecipeAPI;
        this.inMemoryDataAccessUser = inMemoryDataAccessUser;
        this.recipeFactory = recipeFactory;
        this.cookThisOrReRollPresenter = generateRecipeOutputBoundary;

    }



    @Override
    public void execute() {
        this.user = inMemoryDataAccessUser.getActiveUser();
        this.preference = user.getPreference();
        this.randomRecipe = user.getRandomRecipe();
//  the input data will be formatted as a string so we can just add it to the query when we call the API call
        String tags = preference.getTags();
        String intolerances = preference.getIntolerances();
        String apiKey = "a4f06a8b4e114ba3bd568fc678e095df";
        JSONObject recipeJSON = generateRecipeAPI.getRecipes(apiKey,tags, intolerances,20);
        if (recipeJSON == null){
//          prepare fail view
            cookThisOrReRollPresenter.prepareFailView("Error Message:  Most likely No more Api tokens (Fix: wait a day)" );
        }

        else if (recipeJSON.getJSONArray("recipes").isEmpty()){
//          prepare fail view
            cookThisOrReRollPresenter.prepareFailView("Error Message: No recipe meet the specified preference (Fix: reduce preferences)" );
        }
        else{
            JSONArray recipesArray = recipeJSON.getJSONArray("recipes");
            this.randomRecipe.setRandomRecipeList(recipesArray);
            this.randomRecipe.setCurrentRecipeIndex(0);
            user.setRandomRecipe(this.randomRecipe);
            Recipe recipe = getRecipe();
            boolean isFavourite = false;
            for(Recipe recipe1: user.getFavouriteRecipes().getFavouriteRecipes()){
                if(recipe1.getId().equals(recipe.getId())){
                    isFavourite = true;
                    break;
                }
            }
//          prepare success view
            GenerateRecipeOutputData generateRecipeOutputData = new GenerateRecipeOutputData(recipe.getTitle(),recipe.getReadyInMinutes(), recipe.getServings(), recipe.getSummary(), recipe.getExtendedIngredients(), recipe.getExtendedInstructions(), recipe.getRecipeImageURL(), recipe.getId(), isFavourite);;
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
