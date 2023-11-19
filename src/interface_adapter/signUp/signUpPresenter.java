package interface_adapter.signUp;
import interface_adapter.Login.loginState;
import interface_adapter.Login.loginViewModel;
import interface_adapter.ViewManagerModel;
import use_case.signUp.signUpOutputBoundary;
import use_case.signUp.signUpOutputData;

public class signUpPresenter implements signUpOutputBoundary{
    private signUpViewModel signUpVM;
    private ViewManagerModel VMM;
    private String mainView;

    public signUpPresenter(signUpViewModel signUpVM, String main, ViewManagerModel VMM){
        this.signUpVM = signUpVM;
        this.mainView = main;
        this.VMM = VMM;
    }
    @Override
    public void prepareSuccessView(signUpOutputData data) {
        VMM.setActiveView(mainView);
        VMM.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {
        signUpState SignupState = signUpVM.getState();
        SignupState.setError(error);
        signUpVM.firePropertyChanged();
    }

    @Override
    public void prepareEmptyView(String msg) {
        signUpState SignupState = signUpVM.getState();
        SignupState.setEmpty(msg);
        signUpVM.firePropertyChanged();
    }
}
