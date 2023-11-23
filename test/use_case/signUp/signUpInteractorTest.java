package use_case.signUp;
import data_access.InMemoryDataAccess.InMemoryDataAccessUser;
import data_access.InMemoryDataAccess.InMemoryDataAccessUserInterface;
import entity.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

public class signUpInteractorTest {

    @Test
    public void interactorExecutes (){
        // Mock dependencies
        User mockUser = new User("test", "test");
        InMemoryDataAccessUserInterface mockInMemoryDataAccessUser = new InMemoryDataAccessUser();
        mockInMemoryDataAccessUser.setActiveUser(mockUser);
        signUpOutputBoundary mockPres = new signUpOutputBoundary() {
            @Override
            public void prepareSuccessView(signUpOutputData data) {
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
        signUpDataAccessInterface mockDAO = new signUpDataAccessInterface() {
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
        signUpInteractor interactor = new signUpInteractor(mockDAO, mockPres, mockInMemoryDataAccessUser);
        signUpInputData data = new signUpInputData("test", "Success", "test");
        interactor.execute(data);
        signUpInputData data2 = new signUpInputData("test", "Empty", "test");
        interactor.execute(data2);
        signUpInputData data3 = new signUpInputData("test", "test", "test");
        interactor.execute(data3);
    }
}
