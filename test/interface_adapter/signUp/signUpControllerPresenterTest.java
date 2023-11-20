package interface_adapter.signUp;
import entity.User;
import interface_adapter.ViewManagerModel;
import org.junit.jupiter.api.Test;
import use_case.signUp.signUpInputBoundary;
import use_case.signUp.signUpInputData;
import use_case.signUp.signUpOutputData;

import static org.junit.jupiter.api.Assertions.*;

public class signUpControllerPresenterTest {
    @Test
    public void presenterTests(){
        signUpViewModel SVM = new signUpViewModel();
        ViewManagerModel VMM = new ViewManagerModel();
        signUpPresenter presenter = new signUpPresenter(SVM,"main", VMM);
        User user = new User("","");
        signUpOutputData data = new signUpOutputData(user);
        presenter.prepareSuccessView(data);
        assertEquals("main", VMM.getActiveView());
        presenter.prepareFailView("Error!");
        signUpState state = SVM.getState();
        assertNotNull(state.getError());
        presenter.prepareEmptyView("Empty");
        state = SVM.getState();
        assertNotNull(state.getEmpty());
        //I dont really need to test states stuff or view stuff -- view is all graphical and states are jsut
        // setters and getters...


    }
    @Test
    public void controllerTest(){
        signUpInputBoundary boundary = new signUpInputBoundary() {
            @Override
            public void execute(signUpInputData s) {
                assertNotNull(s);
                assertNotNull(s.getUsername());
                assertNotNull(s.getPasswordChecker());
                assertNotNull(s.getPassword());


            }
        };
        signUpController controller = new signUpController(boundary);
        controller.execute("User", "Pass", "Pass");
    }
}
