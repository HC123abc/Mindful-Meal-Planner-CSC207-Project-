package interface_adapter.signUp;
import interface_adapter.Login.loginState;
import interface_adapter.Login.loginViewModel;
import interface_adapter.ViewManagerModel;
import use_case.signUp.signUpOutputBoundary;
import use_case.signUp.signUpOutputData;

public class signUpPresenter implements signUpOutputBoundary{
    private signUpViewModel signUpVM;
    private loginViewModel loginVM;
    private ViewManagerModel VMM;

    public signUpPresenter(signUpViewModel signUpVM, loginViewModel loginVM, ViewManagerModel VMM){
        this.signUpVM = signUpVM;
        this.loginVM = loginVM;
        this.VMM = VMM;
    }
    @Override
    public void prepareSuccessView(signUpOutputData data) {
        loginState LoginState = loginVM.getState();
        //loginState.setUsername(data.getUsername());
        this.loginVM.setState(LoginState);
        loginVM.firePropertyChanged();
        VMM.setActiveView(loginVM.getViewName());
        VMM.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {
        signUpState SignupState = signUpVM.getState();
        SignupState.setError(error);
        signUpVM.firePropertyChanged();
    }
}
