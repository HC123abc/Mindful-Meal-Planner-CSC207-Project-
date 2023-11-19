package use_case.signUp;
import data_access.InMemoryDataAccess.InMemoryDataAccessUserInterface;
import entity.User;
import data_access.User.userDataAccessObject; // delete
import interface_adapter.signUp.signUpPresenter;
import data_access.InMemoryDataAccess.InMemoryDataAccessUserInterface;

public class signUpInteractor implements signUpInputBoundary {
    private signUpDataAccessInterface DAO;
    private signUpOutputBoundary signUpPresenter;
    private InMemoryDataAccessUserInterface IMDAU;

    public signUpInteractor(signUpDataAccessInterface DAO, signUpOutputBoundary SOB, InMemoryDataAccessUserInterface IMDAU){
        this.DAO = DAO;
        this.signUpPresenter = SOB;
        this.IMDAU = IMDAU;
    }
    @Override
    public void execute(signUpInputData s){
        User user = DAO.createUser(s.getUsername(), s.getPassword());
        String state = DAO.storeUser(user, s.getPasswordChecker());
        System.out.println(state);
        if (state.equals("Success!")){
            IMDAU.setActiveUser(user);
            signUpOutputData data = new signUpOutputData(user);
            signUpPresenter.prepareSuccessView(data);
        }
        else if (state.equals("Empty Username")){
            signUpPresenter.prepareEmptyView("empty");
        }
        else{
            signUpPresenter.prepareFailView(state);
        }

    }

}

