package interface_adapter.signUp;
import entity.User;
import interface_adapter.ViewManagerModel;
import org.junit.jupiter.api.Test;
import use_case.signUp.SignUpInputBoundary;
import use_case.signUp.SignUpInputData;
import use_case.signUp.SignUpOutputData;

import static org.junit.jupiter.api.Assertions.*;

public class SignUpControllerPresenterTest {
    @Test
    public void presenterTests(){
        SignUpViewModel SVM = new SignUpViewModel();
        ViewManagerModel VMM = new ViewManagerModel();
        SignUpPresenter presenter = new SignUpPresenter(SVM,"main", VMM);
        User user = new User("","");
        SignUpOutputData data = new SignUpOutputData(user);
        presenter.prepareSuccessView(data);
        assertEquals("main", VMM.getActiveView());
        presenter.prepareFailView("Error!");
        SignUpState state = SVM.getState();
        assertNotNull(state.getError());
        presenter.prepareEmptyView("Empty");
        state = SVM.getState();
        assertNotNull(state.getEmpty());
        //I dont really need to test states stuff or view stuff -- view is all graphical and states are jsut
        // setters and getters...
    }
    @Test
    public void controllerTest(){
        SignUpInputBoundary boundary = new SignUpInputBoundary() {
            @Override
            public void execute(SignUpInputData s) {
                assertNotNull(s);
                assertNotNull(s.getUsername());
                assertNotNull(s.getPasswordChecker());
                assertNotNull(s.getPassword());


            }
        };
        SignUpController controller = new SignUpController(boundary);
        controller.execute("User", "Pass", "Pass");
    }
}
