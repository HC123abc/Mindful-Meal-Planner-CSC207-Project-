package data_access.User;

import app.userFactory;
import entity.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.*;
import java.util.ArrayList;
import java.io.FileWriter;   // Import the FileWriter class


public class UserDataAccessObjectTest {
    ArrayList<String> Lines = new ArrayList<>();

    @Before
    public void fileManipulations() throws IOException {
        BufferedWriter myWriter = new BufferedWriter((new FileWriter("./users/userFileTest.txt", true)));
        myWriter.write("Goose");
        myWriter.newLine();
        myWriter.close();
    }
    @Test
    public void testUserSignInFunc(){
        //Success
        userFactory factory = new userFactory();
        User user = factory.createUser("UsernameVERYCOOLUSER", "Password"); // factory
        assertNotNull(user);
        assertEquals("UsernameVERYCOOLUSER", user.getUsername());
        assertEquals(true, user.verifyPassword("Password"));

        UserDataAccessObject UDAO = new UserDataAccessObject(); // sign up to database
        UDAO.txtChange("./users/userFileTest.txt");
        String success = UDAO.storeUser(user, "Password", "Password");
        assertEquals("Success!", success);

        User userEmpty = new User("", "password");
        String empty = UDAO.storeUser(userEmpty, "password", "password");
        assertEquals("Empty", empty);

        User userShort = new User("UsernameGoose", "");
        String shortError = UDAO.storeUser(userShort, "", "");
        assertEquals("Your password is too small. 🦑", shortError);

        User userTaken = new User("Goose", "Goose");
        String takenError = UDAO.storeUser(userTaken, "Goose", "Goose");
        assertEquals("Username already taken", takenError);

        User createUserTest = UDAO.createUser("Username", "Password");
        assertNotNull(createUserTest);

    }


    @Test
    public void testUserLoginFunc(){
        //Success
        UserDataAccessObject UDAO = new UserDataAccessObject(); // sign up to database
        UDAO.txtChange("./users/userFileTest.txt");
        String userStatus = UDAO.getUser("Goose", "Goose");
        User gooseUser = UDAO.setUser();
        assertNotNull(gooseUser);
        assertEquals("Goose", gooseUser.getUsername());
        assertEquals(true, gooseUser.verifyPassword("Goose"));

        String userNone = UDAO.getUser("NotGoose", "NotGoose");
        assertEquals("User does not exist error.", userNone);

        String userBadPass = UDAO.getUser("Goose", "NotGoose");
        assertEquals("Passwords are not matching.", userBadPass);
    }

    @After
    public void fileManipulationsEnd() throws IOException { // want to make sure file is reset to before tests
        File file = new File("./users/UsernameVERYCOOLUSER.json");
        file.delete();
        File file2 = new File("./users/userFileTest.txt");
        file2.delete();
    }

}
