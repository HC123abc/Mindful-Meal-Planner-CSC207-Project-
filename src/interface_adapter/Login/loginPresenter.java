package interface_adapter.Login;
import data_access.InMemoryDataAccess.InMemoryDataAccessUserInterface;
import interface_adapter.ViewManagerModel;
import use_case.login.loginOutputBoundary;

import javax.swing.text.View;

public class loginPresenter implements loginOutputBoundary {
    private loginViewModel LVM;
    private ViewManagerModel VMM;
    private String mainView;


    public loginPresenter(loginViewModel LVM, ViewManagerModel VMM, String mainView){
        this.LVM = LVM;
        this.VMM = VMM;
        this.mainView = mainView;
    }
    @Override
    public void prepareFailView(String uidState) {
        loginState LoginState = LVM.getState();
        LoginState.setError(uidState);
        LVM.firePropertyChanged();
    }

    @Override
    public void prepareSuccessView(String username) {
        loginState LoginState = LVM.getState();
        this.LVM.setState(LoginState);
        LVM.firePropertyChanged();
        VMM.setActiveView(mainView);
        VMM.firePropertyChanged();
    }
}
