package data_access.InMemoryDataAccess;
import entity.User;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

public class InMemoryDataAccessUserTest {

    @Test
    public void inMemoryTest (){
        InMemoryDataAccessUser IMDAU = new InMemoryDataAccessUser();
        User user = new User("User", "Pass");
        IMDAU.setActiveUser(user);
        assertEquals(IMDAU.getActiveUser(), user);

    }

}
