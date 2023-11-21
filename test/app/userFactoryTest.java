package app;
import entity.User;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

public class userFactoryTest {

    @Test
    public void test (){
        userFactory factory = new userFactory();
        User user = factory.createUser("Username", "Factory");
        assertEquals(user.getUsername(), "Username");
        assertEquals(true, user.verifyPassword("Factory"));

    }

}
