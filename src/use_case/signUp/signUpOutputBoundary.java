package use_case.signUp;

public interface signUpOutputBoundary {
    void prepareSuccessView(signUpOutputData data);
    void prepareFailView(String error);
}
