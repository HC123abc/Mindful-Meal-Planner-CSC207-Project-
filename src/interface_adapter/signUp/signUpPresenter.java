package interface_adapter.signUp;
import use_case.signUp.signUpOutputBoundary;
import use_case.signUp.signUpOutputData;

public class signUpPresenter implements signUpOutputBoundary{
    @Override
    public void prepareSuccessView(signUpOutputData data) {

    }

    @Override
    public void prepareFailView(String error) {

    }
}
