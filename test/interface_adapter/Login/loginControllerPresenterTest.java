package interface_adapter.Login;
import interface_adapter.ViewManagerModel;
import org.junit.jupiter.api.Test;
import use_case.login.LoginInputBoundary;
import use_case.login.LoginInputData;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class loginControllerPresenterTest {

    @Test
    public void presenterTests(){
        loginViewModel LVM = new loginViewModel();
        ViewManagerModel VMM = new ViewManagerModel();
        loginPresenter presenter = new loginPresenter(LVM, VMM,"main");
        presenter.prepapreEmptyView();
        assertEquals(LVM.getViewName(), VMM.getActiveView());
        presenter.prepareFailView("Error");
        assertNotNull(LVM.getState().getError());
        presenter.prepareSuccessView("Username");
        assertEquals("main", VMM.getActiveView());
        //I dont really need to test states stuff or view stuff -- view is all graphical and states are jsut
        // setters and getters... (🦆!)
    }
    @Test
    public void controllerTests(){
        LoginInputBoundary LIB = new LoginInputBoundary() {
            @Override
            public void execute(LoginInputData data) {
                assertEquals("Username", data.getUsername());
                assertEquals("Password", data.getPassword());
            }
        };

        loginController controller = new loginController(LIB);
        controller.execute("Username", "Password");
    }
}
