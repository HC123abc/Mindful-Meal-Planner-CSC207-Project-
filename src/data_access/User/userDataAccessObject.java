package dataAccess;
import use_case.signUp.signUpDataAccessInterface;
import app.userFactory;
import entity.User;

public class userDataAccessObject implements signUpDataAccessInterface {
    private void createUser(String username, String password){
        userFactory factory = new userFactory();
        User user = factory.createUser(username, password);
    }
}
