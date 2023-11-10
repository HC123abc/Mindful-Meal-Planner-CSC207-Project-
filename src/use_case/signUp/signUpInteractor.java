package use_case.signUp;
import entity.User;
import data_access.User.userDataAccessObject; // delete
import interface_adapter.signUp.signUpPresenter;

public class signUpInteractor implements signUpInputBoundary {
    private signUpDataAccessInterface DAO;
    private signUpOutputBoundary signUpPresenter;

    public signUpInteractor(signUpDataAccessInterface DAO, signUpOutputBoundary SOB){
        this.DAO = DAO;
        this.signUpPresenter = SOB;
    }
    @Override
    public void execute(signUpInputData s){
        User user = DAO.createUser(s.getUsername(), s.getPassword());
        String state = DAO.storeUser(user, s.getPasswordChecker());
        System.out.println(state);
        if (state.equals("Success!")){
            signUpOutputData data = new signUpOutputData(user);
            signUpPresenter.prepareSuccessView(data);
        }
        else{
            signUpPresenter.prepareFailView(state);
        }

    }

}

