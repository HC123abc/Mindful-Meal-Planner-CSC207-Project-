package use_case.ReturnToPreviousView;

import entity.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ReturnToPreviousViewTests {
    @Test
    public void test(){
        User user = new User("", "");
        ReturnToPreviousViewInputData input = new ReturnToPreviousViewInputData("");
        ReturnToPreviousViewOutputBoundary outputBoundary = new ReturnToPreviousViewOutputBoundary(){
            @Override
            public void prepareSuccessView(ReturnToPreviousViewOutputData outputData) {
                assertNotNull(outputData);
                assertEquals(outputData.getPreviousView(), input.getPreviousView());

            }
        };
        input.setPreviousView("Prev");

        ReturnToPreviousViewInteractor interactor = new ReturnToPreviousViewInteractor(outputBoundary);
        interactor.execute(input);

    }
}
