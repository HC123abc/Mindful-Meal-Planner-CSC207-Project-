package use_case.Finish;

import entity.RandomRecipe;
import entity.Recipe;
import entity.RecipeFactory;
import use_case.reRoll.ReRollInputBoundary;
import use_case.reRoll.ReRollOutputBoundary;
import use_case.reRoll.ReRollOutputData;


public class FinishInteractor implements FinishInputBoundary {

    private FinishOutputBoundary finishPresenter;

    public FinishInteractor(FinishOutputBoundary finishPresenter) {
        this.finishPresenter = finishPresenter;
    }

    @Override
    public void execute() {
        finishPresenter.prepareSuccessView();

    }
}
