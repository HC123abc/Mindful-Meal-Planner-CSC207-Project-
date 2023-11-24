package use_case.login;

import entity.User;

public interface LoginDataAccessInterface {
    String getUser(String user, String password);
    User setUser();
}
