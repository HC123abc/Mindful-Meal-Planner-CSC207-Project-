package use_case.login;

import entity.User;

public interface loginDataAccessInterface {
    String getUser(String user, String password);
    User setUser();
}
