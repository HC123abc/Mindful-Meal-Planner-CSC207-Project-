package use_case.login;
import data_access.InMemoryDataAccess.InMemoryDataAccessUser;
import data_access.InMemoryDataAccess.InMemoryDataAccessUserInterface;
import entity.User;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LoginInteractorTest {

    @Test
    public void loginTests(){
        User mockUser = new User("test", "test");
        InMemoryDataAccessUserInterface mockInMemoryDataAccessUser = new InMemoryDataAccessUser();
        mockInMemoryDataAccessUser.setActiveUser(mockUser);
        LoginDataAccessInterface mockDAO = new LoginDataAccessInterface() {
            @Override
            public String getUser(String user, String password) {
                if (password.equals("Success!")){
                    return "Success!";
                } else if (password.equals("Empty")) {
                    return "";
                } else {
                    return "error";
                }
            }

            @Override
            public User setUser() {
                return new User("","");
            }
        };
        LoginOutputBoundary mockPres = new LoginOutputBoundary() {
            @Override
            public void prepareFailView(String uidState) {
                assertNotNull(uidState);
            }

            @Override
            public void prepareSuccessView(String username) {
                assertEquals("test", username);
            }

            @Override
            public void prepapreEmptyView() {

            }
        };

        LoginInteractor interactor = new LoginInteractor(mockDAO, mockPres, mockInMemoryDataAccessUser);
        LoginInputData data = new LoginInputData("test", "Success!");
        interactor.execute(data);
        LoginInputData data2 = new LoginInputData("", "");
        interactor.execute(data2);
        LoginInputData data3 = new LoginInputData("test", "pass");
        interactor.execute(data3);
    }
}
