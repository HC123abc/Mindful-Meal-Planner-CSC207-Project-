package use_case.signUp;
import entity.User;

public interface SignUpDataAccessInterface {
    User createUser(String Username, String Password);

    String storeUser(User user, String Password, String PasswordCheck);

}
