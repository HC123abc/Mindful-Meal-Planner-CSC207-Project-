package interface_adapter.ReturnToPreviousView;



import interface_adapter.ViewManagerModel;
import use_case.ReturnToPreviousView.ReturnToPreviousViewOutputBoundary;
import use_case.ReturnToPreviousView.ReturnToPreviousViewOutputData;


public class ReturnToPreviousViewPresenter implements ReturnToPreviousViewOutputBoundary {
    private ViewManagerModel viewManagerModel;
    private ReturnToPreviousViewViewModel previousViewViewModel;
    public ReturnToPreviousViewPresenter(ViewManagerModel viewManagerModel, ReturnToPreviousViewViewModel previousViewViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.previousViewViewModel = previousViewViewModel;
    }


    @Override
    public void prepareSuccessView(ReturnToPreviousViewOutputData outputData) {
        ReturnToPreviousViewState state = previousViewViewModel.getState();
        state.setPrevious(outputData.getPreviousView());
        previousViewViewModel.setState(state);
        viewManagerModel.setActiveView(outputData.getPreviousView());
        viewManagerModel.firePropertyChanged();
    }
}
