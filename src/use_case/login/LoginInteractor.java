package use_case.login;

import data_access.InMemoryDataAccess.InMemoryDataAccessUserInterface;
import entity.User;

public class LoginInteractor implements LoginInputBoundary {
    private LoginDataAccessInterface DAO;
    private LoginOutputBoundary loginPresenter;
    private InMemoryDataAccessUserInterface IMDAU;

    public LoginInteractor(LoginDataAccessInterface DAO, LoginOutputBoundary pres, InMemoryDataAccessUserInterface IMDAU){
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

            System.out.println(IMDAU.getActiveUser().getPreference().getSelectedCuisines());
            loginPresenter.prepareSuccessView(data.getUsername());
        } else {
            loginPresenter.prepareFailView(UIDState);
        }
    }
}
