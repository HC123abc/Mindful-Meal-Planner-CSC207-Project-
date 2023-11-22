package use_case.reRoll;

import data_access.InMemoryDataAccess.InMemoryDataAccessUserInterface;
import entity.*;


public class ReRollInteractor implements ReRollInputBoundary {
    private RandomRecipe randomRecipe;
    private User user;
    private RecipeFactory recipeFactory;
    private ReRollOutputBoundary reRollPresenter;
    private InMemoryDataAccessUserInterface inMemoryDataAccessUser;

    public ReRollInteractor(InMemoryDataAccessUserInterface inMemoryDataAccessUser, ReRollOutputBoundary reRollPresenter, RecipeFactory recipeFactory) {
        this.inMemoryDataAccessUser = inMemoryDataAccessUser;
        this.recipeFactory = recipeFactory;
        this.reRollPresenter = reRollPresenter;
    }

    @Override
    public void execute() {
        this.user = inMemoryDataAccessUser.getActiveUser();
        this.randomRecipe = user.getRandomRecipe();
        Recipe recipe = getRecipe();
        boolean isFavourite = false;
        for(Recipe recipe1: user.getFavouriteRecipes().getFavouriteRecipes()){
            if(recipe1.getId().equals(recipe.getId())){
                isFavourite = true;
                break;
            }
        }
        ReRollOutputData reRollOutputData = new ReRollOutputData(recipe.getTitle(), recipe.getReadyInMinutes(), recipe.getServings(), recipe.getSummary(), recipe.getExtendedIngredients(), recipe.getExtendedInstructions(), recipe.getRecipeImageURL(), recipe.getId(), isFavourite);
        reRollPresenter.prepareSuccessView(reRollOutputData);

    }

    private Recipe getRecipe() {
        int current_i = randomRecipe.getCurrentRecipeIndex();
        System.out.println((current_i));
        if (current_i == randomRecipe.getRandomRecipeList().length() - 1) {
            randomRecipe.setCurrentRecipeIndex(0);
        } else {
            randomRecipe.setCurrentRecipeIndex(current_i + 1);
        }
        Recipe recipe = recipeFactory.create(randomRecipe.getRandomRecipeList().getJSONObject(current_i));

        return recipe;
    }
}
