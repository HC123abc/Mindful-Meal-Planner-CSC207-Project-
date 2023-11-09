package use_case.signUp;
import entity.User;
import data_access.User.userDataAccessObject; // delete

public class signUpInteractor implements signUpInputBoundary {
    signUpDataAccessInterface DAO;
    signUpOutputBoundary signUpPresenter;

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

    public static void main(String[] args) {
        userDataAccessObject n = new userDataAccessObject();
        signUpInteractor V = new signUpInteractor(n);
        signUpInputData d = new signUpInputData("woo", "pass", "pass");
        V.execute(d);
    }
}

