package use_case.login;

import java.rmi.server.UID;

public class LoginInteractor implements LoginInputBoundary {
    private loginDataAccessInterface DAO;
    private loginOutputBoundary loginPresenter;

    public LoginInteractor(loginDataAccessInterface DAO, loginOutputBoundary pres){
        this.DAO = DAO;
        this.loginPresenter = pres;
    }

    @Override
    public void execute(LoginInputData data) {
        String UIDState = DAO.getUser(data.getUsername(), data.getPassword());
        if (UIDState.equals("Success!")){
            loginPresenter.prepareSuccessView(data.getUsername());
        } else {
            loginPresenter.prepareFailView(UIDState);
        }
    }
}
