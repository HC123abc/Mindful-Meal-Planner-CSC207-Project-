package use_case.signUp;
import entity.User;
import kotlin.text.UStringsKt;

public interface signUpDataAccessInterface {
    User createUser(String Username, String Password);

    String storeUser(User user, String PasswordCheck);

}
