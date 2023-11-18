package data_access.InMemoryDataAccess;

import entity.User;

public class InMemoryDataAccessUser implements InMemoryDataAccessUserInterface {
    private User activeUser;

    public void setActiveUser(User user) {
        activeUser = user;
    }

    public User getActiveUser() {
        return activeUser;
    }
}
