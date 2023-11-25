package interface_adapter.ReturnToPreviousView;
import interface_adapter.ViewManagerModel;
import org.junit.jupiter.api.Test;
import use_case.ReturnToPreviousView.ReturnToPreviousViewInputBoundary;
import use_case.ReturnToPreviousView.ReturnToPreviousViewInputData;
import use_case.ReturnToPreviousView.ReturnToPreviousViewOutputData;
import static org.junit.jupiter.api.Assertions.*;

import javax.swing.text.View;


public class ReturnToPreviousViewTests {

    @Test
    public void tests(){
        ReturnToPreviousViewViewModel vm = new ReturnToPreviousViewViewModel();
        ViewManagerModel vmm = new ViewManagerModel();
        ReturnToPreviousViewPresenter pres = new ReturnToPreviousViewPresenter(vmm, vm);
        ReturnToPreviousViewOutputData data = new ReturnToPreviousViewOutputData("Goose");
        pres.prepareSuccessView(data);
        assertNotNull(data);
        assertEquals(data.getPreviousView(), vmm.getActiveView());
        assertNotNull(vm.getState().getPrevious());

        ReturnToPreviousViewInputBoundary input = new ReturnToPreviousViewInputBoundary() {
            @Override
            public void execute(ReturnToPreviousViewInputData inputData) {
                assertEquals(inputData.getPreviousView(), "previous");
            }
        };
        ReturnToPreviousViewController controller = new ReturnToPreviousViewController(input);
        controller.execute("previous");
    }
}
