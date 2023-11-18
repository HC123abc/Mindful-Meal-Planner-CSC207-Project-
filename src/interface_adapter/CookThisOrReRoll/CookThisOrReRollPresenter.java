package interface_adapter.CookThisOrReRoll;

import interface_adapter.ViewManagerModel;
import use_case.generateRecipe.GenerateRecipeOutputBoundary;
import use_case.generateRecipe.GenerateRecipeOutputData;

public class CookThisOrReRollPresenter implements GenerateRecipeOutputBoundary {
    private final CookThisOrReRollViewModel cookThisOrReRollViewModel;
    private ViewManagerModel viewManagerModel;

    public CookThisOrReRollPresenter(CookThisOrReRollViewModel cookThisOrReRollViewModel, ViewManagerModel viewManagerModel) {
        this.cookThisOrReRollViewModel = cookThisOrReRollViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void prepareSuccessView(GenerateRecipeOutputData recipeOutputData) {
        CookThisOrReRollState cookThisOrReRollState = cookThisOrReRollViewModel.getState();
        cookThisOrReRollState.setTitle(recipeOutputData.getTitle());
        cookThisOrReRollState.setServings(recipeOutputData.getServings());
        cookThisOrReRollState.setImage(recipeOutputData.getRecipeImageURL());
        cookThisOrReRollState.setSummary(recipeOutputData.getSummary());
        cookThisOrReRollState.setReadyInMinutes(recipeOutputData.getReadyInMinutes());
        cookThisOrReRollState.setIngredients(recipeOutputData.getExtendedIngredients());
        cookThisOrReRollState.setInstruction(recipeOutputData.getExtendedInstructions());
        cookThisOrReRollState.setId(recipeOutputData.getId());
        this.cookThisOrReRollViewModel.setState(cookThisOrReRollState);
        cookThisOrReRollViewModel.firePropertyChanged();
        viewManagerModel.setActiveView(cookThisOrReRollViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {
//      main view model
        CookThisOrReRollState cookThisOrReRollState = cookThisOrReRollViewModel.getState();
        cookThisOrReRollState.setRecipeError(error);
        cookThisOrReRollViewModel.firePropertyChanged();

    }
}
