package interface_adapter.CookThis;

import interface_adapter.ViewManagerModel;
import use_case.cookThis.CookThisOutputBoundary;
import use_case.cookThis.CookThisOutputData;


public class CookThisPresenter implements CookThisOutputBoundary {

    private final CookThisViewModel cookThisViewModel;
    private ViewManagerModel viewManagerModel;
    public CookThisPresenter(CookThisViewModel cookThisViewModel, ViewManagerModel viewManagerModel) {
        this.cookThisViewModel = cookThisViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void prepareSuccessView(CookThisOutputData cookThisOutputData) {
        CookThisState cookThisState = cookThisViewModel.getState();
        cookThisState.setInstruction(cookThisOutputData.getInstruction());
        cookThisState.setIngredientsWithImage(cookThisOutputData.getIngredientsWithImage());
        this.cookThisViewModel.setState(cookThisState);
        cookThisViewModel.firePropertyChanged();
        viewManagerModel.setActiveView(cookThisViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
