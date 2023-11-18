package interface_adapter.Login;

import use_case.login.LoginInputBoundary;
import use_case.login.LoginInputData;
import use_case.signUp.signUpInputBoundary;
import use_case.signUp.signUpInputData;

public class loginController {
    private LoginInputBoundary loginInputBoundary;

    public loginController(LoginInputBoundary loginInteractor){
        this.loginInputBoundary = loginInteractor;
    }

    public void execute(String username, String password){
        LoginInputData data = new LoginInputData(username, password);
        loginInputBoundary.execute(data);
    }
}
