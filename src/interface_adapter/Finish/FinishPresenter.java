package interface_adapter.Finish;


import interface_adapter.ViewManagerModel;
import use_case.Finish.FinishOutputBoundary;

public class FinishPresenter implements FinishOutputBoundary {
    private ViewManagerModel viewManagerModel;
    private FinishViewModel finishViewModel;
    public FinishPresenter(ViewManagerModel viewManagerModel, FinishViewModel finishViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.finishViewModel = finishViewModel;
    }


    @Override
    public void prepareSuccessView() {
        viewManagerModel.setActiveView(finishViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
