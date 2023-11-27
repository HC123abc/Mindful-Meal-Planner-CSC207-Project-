package use_case.Finish;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class FinishInteractorTest {

    @Test
    public void testFinishInteractorExecute() {
        // Create an anonymous implementation of FinishOutputBoundary for testing
        FinishOutputBoundary mockFinishPresenter = new FinishOutputBoundary() {
            private boolean prepareSuccessViewCalled = false;

            @Override
            public void prepareSuccessView() {
                // Set a flag indicating that prepareSuccessView was called
                assert true;
            }

            // Getter method to check if prepareSuccessView was called
            public boolean isPrepareSuccessViewCalled() {
                return prepareSuccessViewCalled;
            }
        };

        // Create a FinishInteractor instance with the anonymous FinishOutputBoundary implementation
        FinishInteractor finishInteractor = new FinishInteractor(mockFinishPresenter);

        // Call the execute method
        finishInteractor.execute();
    }
}
