package interface_adapter.signUp;
import use_case.signUp.signUpInputBoundary;
import use_case.signUp.signUpInputData;

public class signUpController {
    private signUpInputBoundary signUpInteractor;

    public signUpController(signUpInputBoundary signUpInteractor){
        this.signUpInteractor = signUpInteractor;
    }

    public void execute(String username, String password, String passCheck){
        signUpInputData data = new signUpInputData(username, password, passCheck);
        signUpInteractor.execute(data);
    }
}
