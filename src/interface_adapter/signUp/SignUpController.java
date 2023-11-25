package interface_adapter.signUp;
import use_case.signUp.SignUpInputBoundary;
import use_case.signUp.SignUpInputData;

public class SignUpController {
    private SignUpInputBoundary signUpInteractor;

    public SignUpController(SignUpInputBoundary signUpInteractor){
        this.signUpInteractor = signUpInteractor;
    }

    public void execute(String username, String password, String passCheck){
        SignUpInputData data = new SignUpInputData(username, password, passCheck);
        signUpInteractor.execute(data);
    }
}
