package use_case.login;

import data_access.InMemoryDataAccess.InMemoryDataAccessUserInterface;
import entity.User;

import java.rmi.server.UID;

public class LoginInteractor implements LoginInputBoundary {
    private loginDataAccessInterface DAO;
    private loginOutputBoundary loginPresenter;
    private InMemoryDataAccessUserInterface IMDAU;

    public LoginInteractor(loginDataAccessInterface DAO, loginOutputBoundary pres, InMemoryDataAccessUserInterface IMDAU){
        this.DAO = DAO;
        this.IMDAU = IMDAU;
        this.loginPresenter = pres;
    }

    @Override
    public void execute(LoginInputData data) {
        String UIDState = DAO.getUser(data.getUsername(), data.getPassword());
        if (data.getUsername().equals("")){
            loginPresenter.prepapreEmptyView();
        }
        else if (UIDState.equals("Success!")){
            User cloneUser = DAO.setUser();
            IMDAU.setActiveUser(cloneUser);
            loginPresenter.prepareSuccessView(data.getUsername());
        } else {
            loginPresenter.prepareFailView(UIDState);
        }
    }
}
