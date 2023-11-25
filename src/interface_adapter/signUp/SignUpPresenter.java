package interface_adapter.signUp;
import interface_adapter.ViewManagerModel;
import use_case.signUp.SignUpOutputBoundary;
import use_case.signUp.SignUpOutputData;

public class SignUpPresenter implements SignUpOutputBoundary {
    private SignUpViewModel signUpVM;
    private ViewManagerModel VMM;
    private String mainView;

    public SignUpPresenter(SignUpViewModel signUpVM, String main, ViewManagerModel VMM){
        this.signUpVM = signUpVM;
        this.mainView = main;
        this.VMM = VMM;
    }
    @Override
    public void prepareSuccessView(SignUpOutputData data) {
        VMM.setActiveView(mainView);
        VMM.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {
        SignUpState SignupState = signUpVM.getState();
        SignupState.setError(error);
        System.out.println(SignupState.getError());
        signUpVM.firePropertyChanged();
    }

    @Override
    public void prepareEmptyView(String msg) {
        SignUpState SignupState = signUpVM.getState();
        SignupState.setEmpty(msg);
        signUpVM.firePropertyChanged();
        VMM.setActiveView(signUpVM.getViewName());
        VMM.firePropertyChanged();
    }
}
