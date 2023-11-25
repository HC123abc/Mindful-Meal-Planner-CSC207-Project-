package interface_adapter.Login;
import interface_adapter.ViewManagerModel;
import use_case.login.LoginOutputBoundary;

public class LoginPresenter implements LoginOutputBoundary {
    private LoginViewModel LVM;
    private ViewManagerModel VMM;
    private String mainView;


    public LoginPresenter(LoginViewModel LVM, ViewManagerModel VMM, String mainView){
        this.LVM = LVM;
        this.VMM = VMM;
        this.mainView = mainView;
    }
    @Override
    public void prepareFailView(String uidState) {
        LoginState LoginState = LVM.getState();
        LoginState.setError(uidState);
        LVM.firePropertyChanged();
    }

    @Override
    public void prepareSuccessView(String username) {
        LoginState LoginState = LVM.getState();
        LoginState.setError(null);
        this.LVM.setState(LoginState);
        LVM.firePropertyChanged();
        VMM.setActiveView(mainView);
        VMM.firePropertyChanged();
    }

    @Override
    public void prepapreEmptyView() {
        VMM.setActiveView(LVM.getViewName());
        VMM.firePropertyChanged();
    }
}
