package interface_adapter.Login;

import use_case.login.LoginInputBoundary;
import use_case.login.LoginInputData;

public class LoginController {
    private LoginInputBoundary loginInputBoundary;

    public LoginController(LoginInputBoundary loginInteractor){
        this.loginInputBoundary = loginInteractor;
    }

    public void execute(String username, String password){
        LoginInputData data = new LoginInputData(username, password);
        loginInputBoundary.execute(data);
    }
}
