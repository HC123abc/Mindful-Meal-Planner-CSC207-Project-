package interface_adapter.ReRoll;

import interface_adapter.CookThisOrReRoll.CookThisOrReRollState;
import interface_adapter.CookThisOrReRoll.CookThisOrReRollViewModel;
import interface_adapter.ViewManagerModel;

import use_case.reRoll.ReRollOutputBoundary;
import use_case.reRoll.ReRollOutputData;

public class ReRollPresenter implements ReRollOutputBoundary {
    private final CookThisOrReRollViewModel cookThisOrReRollViewModel;
    private ViewManagerModel viewManagerModel;
    public ReRollPresenter(CookThisOrReRollViewModel cookThisOrReRollViewModel, ViewManagerModel viewManagerModel) {
        this.cookThisOrReRollViewModel = cookThisOrReRollViewModel;
        this.viewManagerModel = viewManagerModel;
    }


    @Override
    public void prepareSuccessView(ReRollOutputData recipeOutputData) {
        CookThisOrReRollState cookThisOrReRollState = cookThisOrReRollViewModel.getState();
        cookThisOrReRollState.setTitle(recipeOutputData.getTitle());
        cookThisOrReRollState.setServings(recipeOutputData.getServings());
        cookThisOrReRollState.setImage(recipeOutputData.getRecipeImageURL());
        cookThisOrReRollState.setSummary(recipeOutputData.getSummary());
        cookThisOrReRollState.setReadyInMinutes(recipeOutputData.getReadyInMinutes());
        cookThisOrReRollState.setIngredients(recipeOutputData.getExtendedIngredients());
        cookThisOrReRollState.setInstruction(recipeOutputData.getExtendedInstructions());
        this.cookThisOrReRollViewModel.setState(cookThisOrReRollState);
        cookThisOrReRollViewModel.firePropertyChanged();
    }
}
