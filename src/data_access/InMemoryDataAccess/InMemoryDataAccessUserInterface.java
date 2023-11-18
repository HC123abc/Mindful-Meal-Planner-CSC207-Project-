package data_access.InMemoryDataAccess;

import entity.User;
import org.json.JSONObject;

public interface InMemoryDataAccessUserInterface {
    public void setActiveUser(User user);

    public User getActiveUser();
}
