package use_case.signUp;
import entity.User;
import data_access.User.userDataAccessObject;

public class signUpInteractor implements signUpInputBoundary {
    signUpDataAccessInterface DAO;

    public signUpInteractor(signUpDataAccessInterface DAO){
        this.DAO = DAO;
    }
    @Override
    public void execute(signUpInputData s){
        User user = DAO.createUser(s.getUsername(), s.getPassword());
        String state = DAO.storeUser(user);
        System.out.println(state);
    }

    public static void main(String[] args) {
        userDataAccessObject n = new userDataAccessObject();
        signUpInteractor V = new signUpInteractor(n);
        signUpInputData d = new signUpInputData("woo", "pass");
        V.execute(d);
    }
}

