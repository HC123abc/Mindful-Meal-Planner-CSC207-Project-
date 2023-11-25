package use_case.signUp;
import data_access.InMemoryDataAccess.InMemoryDataAccessUser;
import data_access.InMemoryDataAccess.InMemoryDataAccessUserInterface;
import entity.User;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SignUpInteractorTest {

    @Test
    public void interactorExecutes (){
        // Mock dependencies
        User mockUser = new User("test", "test");
        InMemoryDataAccessUserInterface mockInMemoryDataAccessUser = new InMemoryDataAccessUser();
        mockInMemoryDataAccessUser.setActiveUser(mockUser);
        SignUpOutputBoundary mockPres = new SignUpOutputBoundary() {
            @Override
            public void prepareSuccessView(SignUpOutputData data) {
                assertNotNull(data);
                assertNotNull(data.getUsername());
            }

            @Override
            public void prepareFailView(String error) {
                assertNotNull(error);
            }

            @Override
            public void prepareEmptyView(String msg) {
                assertNotNull(msg);
            }
        };
        SignUpDataAccessInterface mockDAO = new SignUpDataAccessInterface() {
            @Override
            public User createUser(String Username, String Password) {
                return new User(Username, Password);
            }

            @Override
            public String storeUser(User user, String Password, String PasswordCheck) {
                if (Password.equals("Success")) {
                    return "Success!";
                } else if (Password.equals("Empty")) {
                    return "Empty";
                }
                return "Error";
            }
        };
        SignUpInteractor interactor = new SignUpInteractor(mockDAO, mockPres, mockInMemoryDataAccessUser);
        SignUpInputData data = new SignUpInputData("test", "Success", "test");
        interactor.execute(data);
        SignUpInputData data2 = new SignUpInputData("test", "Empty", "test");
        interactor.execute(data2);
        SignUpInputData data3 = new SignUpInputData("test", "test", "test");
        interactor.execute(data3);
    }
}
