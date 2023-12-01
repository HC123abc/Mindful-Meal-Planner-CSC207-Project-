package app;

import interface_adapter.CookThis.CookThisViewModel;
import interface_adapter.Finish.FinishController;
import interface_adapter.Finish.FinishPresenter;
import interface_adapter.Finish.FinishViewModel;
import interface_adapter.ReturnToPreviousView.ReturnToPreviousViewController;
import interface_adapter.ReturnToPreviousView.ReturnToPreviousViewPresenter;
import interface_adapter.ReturnToPreviousView.ReturnToPreviousViewViewModel;
import interface_adapter.ViewManagerModel;
import use_case.Finish.FinishInteractor;
import use_case.ReturnToPreviousView.ReturnToPreviousViewInteractor;
import view.CookThisOrReRollView;
import view.CookThisView;

public class CookThisViewFactory {
    public static CookThisView createView(ViewManagerModel viewManagerModel, CookThisViewModel cookThisViewModel) {
        FinishViewModel finishViewModel = new FinishViewModel();
        FinishPresenter finishPresenter = new FinishPresenter(viewManagerModel,finishViewModel);
        FinishInteractor finishInteractor = new FinishInteractor(finishPresenter);
        FinishController finishController = new FinishController(finishInteractor);
        ReturnToPreviousViewViewModel returnToPreviousViewViewModel = new ReturnToPreviousViewViewModel();
        ReturnToPreviousViewPresenter returnToPreviousViewPresenter = new ReturnToPreviousViewPresenter(viewManagerModel,returnToPreviousViewViewModel);
        ReturnToPreviousViewInteractor returnToPreviousViewInteractor = new ReturnToPreviousViewInteractor(returnToPreviousViewPresenter);
        ReturnToPreviousViewController returnToPreviousViewController = new ReturnToPreviousViewController(returnToPreviousViewInteractor);
        CookThisView cookThisView = new CookThisView(cookThisViewModel, finishController, returnToPreviousViewController);
        return cookThisView;
    }
}
